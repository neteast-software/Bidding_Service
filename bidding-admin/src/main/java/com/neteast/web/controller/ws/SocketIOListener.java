package com.neteast.web.controller.ws;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.neteast.business.domain.bid.*;
import com.neteast.framework.websockt.bean.Custom;
import com.neteast.framework.websockt.bean.OperaRecord;
import com.neteast.framework.websockt.handler.SocketIOMessageEventHandler;
import com.neteast.framework.websockt.service.SocketIOService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lzp
 * @date 2023年11月28 17:43
 */

@Slf4j
@AllArgsConstructor
public class SocketIOListener implements DataListener<String> {

    private SocketIOServer socketIOServer;

    /** channel作为key */
    public static ConcurrentHashMap<String,List<ExpertBidMsg>> map = new ConcurrentHashMap<>();

    @Override
    public void onData(SocketIOClient client, String s, AckRequest ackRequest){
        log.info("客户端-{},发送消息-{}",client.getRemoteAddress(),s);
        OperaRecord operaRecord = JSONObject.parseObject(s,OperaRecord.class);
        if (operaRecord!=null){
            operaRecord.setTime(new Date());
            String receiver = operaRecord.getReceiver();
            String channel = operaRecord.getChannel();
            //设置用户选择的信息
            setExpertSelectRecord(operaRecord);
            //获取接收方
            List<Custom> customs = SocketIOMessageEventHandler.getSocketIOByRole(receiver,channel);
            //记录操作
            SocketIOMessageEventHandler.setOperaRecord(operaRecord.getChannel(),operaRecord);
            customs.forEach(o->{
                //实时操作状态
                SocketIOClient socketIOClient = socketIOServer.getClient(o.getUuid());
                String body = JSON.toJSONString(operaRecord);
                SocketIOService.sendMsg(o,body,socketIOClient);
            });
        }
    }

    /**
     * @Description 设置用户保存记录
     * @author lzp
     * @Date 2023/12/11
     */
    private static void setExpertSelectRecord(OperaRecord operaRecord){

        String channel = operaRecord.getChannel();
        List<ExpertBidMsg> list = map.get(channel);
        if (list != null){
            for (ExpertBidMsg expertBidMsg:list) {
                if (expertBidMsg.getId()==operaRecord.getUserId()&&
                        expertBidMsg.getSupplierId()==operaRecord.getSupplierId()&&
                        expertBidMsg.getPackageId()==operaRecord.getPackageId()){
                    setExpertBidMsg(expertBidMsg,operaRecord);
                    return;
                }
            }
        }else {
            list = new ArrayList<>();
        }
        ExpertBidMsg expertBidMsg = new ExpertBidMsg();
        setExpertBidMsg(expertBidMsg,operaRecord);
        list.add(expertBidMsg);
        map.put(channel,list);
    }

    private static ExpertBidMsg setExpertBidMsg(ExpertBidMsg expertBidMsg,OperaRecord operaRecord){

        expertBidMsg.setId(operaRecord.getUserId());
        expertBidMsg.setName(operaRecord.getUserName());
        expertBidMsg.setSupplierId(operaRecord.getSupplierId());
        expertBidMsg.setPackageId(operaRecord.getPackageId());
        String type = operaRecord.getOperaType();
        String opera = operaRecord.getRecord();
        log.info("操作记录的值为-{}",opera);
        switch (type){
            case "commercial":
                Commercial commercial = JSONObject.parseObject(opera, Commercial.class);
                expertBidMsg.setCommercials(commercial);
                break;
            case "price":
                Price price = JSONObject.parseObject(opera,Price.class);
                expertBidMsg.setPrices(price);
                break;
            case "qualification":
                Qualification qualification = JSONObject.parseObject(opera,Qualification.class);
                expertBidMsg.setQualifications(qualification);
                break;
            case "technical":
                Technical technical = JSONObject.parseObject(opera,Technical.class);
                expertBidMsg.setTechnicals(technical);
        }
        return expertBidMsg;
    }
}
