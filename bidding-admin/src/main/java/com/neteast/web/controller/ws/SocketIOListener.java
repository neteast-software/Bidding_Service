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

    /** channel作为key 专家端数据 */
    public static ConcurrentHashMap<String,List<ExpertBidMsg>> map = new ConcurrentHashMap<>();

    /** channel作为key 供应商数据 */
    public static ConcurrentHashMap<String,List<SupplierBidMsg>> supplierMap = new ConcurrentHashMap<>();

    /** 会议结束 清除通道数据 */
    public static void clear(String channelName){
        map.remove(channelName);
        supplierMap.remove(channelName);
    }

    @Override
    public void onData(SocketIOClient client, String s, AckRequest ackRequest){
        log.info("客户端-{},发送消息-{}",client.getRemoteAddress(),s);
        OperaRecord operaRecord = JSONObject.parseObject(s,OperaRecord.class);
        if (operaRecord!=null){
            operaRecord.setTime(new Date());
            String receiver = operaRecord.getReceiver();
            String channel = operaRecord.getChannel();
            //设置用户选择的信息
            Score score = setExpertSelectRecord(operaRecord);
            //获取接收方
            List<Custom> customs = SocketIOMessageEventHandler.getSocketIOByRole(receiver,channel);
            //记录操作
            SocketIOMessageEventHandler.setOperaRecord(operaRecord.getChannel(),operaRecord);

            String body = messageHandler(receiver,operaRecord,score);
            customs.forEach(o->{
                //实时操作状态
                SocketIOClient socketIOClient = socketIOServer.getClient(o.getUuid());
                SocketIOService.sendMsg(o, body,socketIOClient);
            });
        }
    }

    private String messageHandler(String receiver,OperaRecord operaRecord,Score score){

        String temp = "";
        switch (receiver){
            //发送主持人端信息
            case "client":
                CompletionStatus completionStatus = getCompletionStatus(score);
                completionStatus.setUserId(operaRecord.getUserId());
                completionStatus.setName(operaRecord.getUserName());
                completionStatus.setSupplierId(operaRecord.getSupplierId());
                temp = JSONObject.toJSONString(completionStatus);
                setSupplierBidRecord(operaRecord,completionStatus);
                break;
            //发送专家端
            case "user":
                temp = operaRecord.getRecord();
        }

        return temp;
    }

    /**
     * @Description 设置用户保存记录
     * @author lzp
     * @Date 2023/12/11
     */
    private static Score setExpertSelectRecord(OperaRecord operaRecord){

        String channel = operaRecord.getChannel();
        List<ExpertBidMsg> list = map.get(channel);
        if (list != null){
            for (ExpertBidMsg expertBidMsg:list) {
                if (expertBidMsg.getId()==operaRecord.getUserId()&&
                        expertBidMsg.getSupplierId()==operaRecord.getSupplierId()&&
                        expertBidMsg.getPackageId()==operaRecord.getPackageId()){
                    return setExpertBidMsg(expertBidMsg,operaRecord);
                }
            }
        }else {
            list = new ArrayList<>();
        }
        ExpertBidMsg expertBidMsg = new ExpertBidMsg();
        Score score = setExpertBidMsg(expertBidMsg,operaRecord);
        list.add(expertBidMsg);
        map.put(channel,list);
        return score;
    }

    private static void setSupplierBidRecord(OperaRecord operaRecord,CompletionStatus completionStatus){

        String channel = operaRecord.getChannel();
        List<SupplierBidMsg> list = supplierMap.get(channel);
        if (list != null){
            for (SupplierBidMsg supplierBidMsg:list){
                if (supplierBidMsg.getSupplierId()==operaRecord.getSupplierId()){
                    setSupplierBidMsg(supplierBidMsg,operaRecord,completionStatus);
                    return;
                }
            }
        }else {
            list = new ArrayList<>();
        }
        SupplierBidMsg supplierBidMsg = new SupplierBidMsg();
        setSupplierBidMsg(supplierBidMsg,operaRecord,completionStatus);
        list.add(supplierBidMsg);
        supplierMap.put(channel,list);
    }

    private static void setSupplierBidMsg(SupplierBidMsg supplierBidMsg,OperaRecord operaRecord,CompletionStatus completionStatus){
        supplierBidMsg.setSupplierId(operaRecord.getSupplierId());
        supplierBidMsg.setSupplierName(operaRecord.getSupplierName());
        supplierBidMsg.setPackageId(operaRecord.getPackageId());
        List<TotalScore> list = supplierBidMsg.getTotalScores();
        String itemType = operaRecord.getItemType();
        Integer inputType  = operaRecord.getType();
        for (TotalScore t:list){
            if (itemType.equals(t.getItemType())){
                Integer temp = t.getNum();
                t.setCompletionStatus(completionStatus);
                temp = supplierBidMsg.getNum() - temp + t.getNum();
                supplierBidMsg.setNum(temp);
                return;
            }
        }
        TotalScore totalScore = new TotalScore();
        totalScore.setType(inputType);
        totalScore.setItemType(itemType);
        totalScore.setCompletionStatus(completionStatus);
        supplierBidMsg.setTotalScores(totalScore);
    }

    private static Score setExpertBidMsg(ExpertBidMsg expertBidMsg,OperaRecord operaRecord){

        expertBidMsg.setId(operaRecord.getUserId());
        expertBidMsg.setName(operaRecord.getUserName());
        expertBidMsg.setSupplierId(operaRecord.getSupplierId());
        expertBidMsg.setPackageId(operaRecord.getPackageId());
        String opera = operaRecord.getRecord();
        log.info("操作记录的值为-{}",opera);
        String itemType = operaRecord.getItemType();
        Integer inputType  = operaRecord.getType();
        Item item = JSONObject.parseObject(opera,Item.class);
        List<Score> scores = expertBidMsg.getReviewStatus();
        for (Score s:scores) {
            if (itemType.equals(s.getItemType())){
                s.setType(inputType);
                s.setList(item);
                return s;
            }
        }
        Score score = new Score();
        score.setType(inputType);
        score.setItemType(itemType);
        score.setList(item);
        expertBidMsg.setReviewStatus(score);
        return score;
    }

    private static CompletionStatus getCompletionStatus(Score score){
        CompletionStatus completionStatus = new CompletionStatus();
        completionStatus.setNum(score.getList().size());
        if (score.getType()==1){
            completionStatus.setPass(score.getPass());
        }else {
            completionStatus.setValue(score.getValue());
        }
        return completionStatus;
    }

}
