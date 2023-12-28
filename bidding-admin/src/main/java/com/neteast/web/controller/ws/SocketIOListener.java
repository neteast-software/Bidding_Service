package com.neteast.web.controller.ws;

import com.alibaba.fastjson2.JSONObject;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.neteast.business.domain.bid.*;
import com.neteast.business.domain.bid.score.GradeItem;
import com.neteast.business.domain.bid.score.PriceScore;
import com.neteast.business.domain.bid.score.RadioItem;
import com.neteast.business.domain.bid.score.SelectItem;
import com.neteast.business.service.ExpertOperaRecordService;
import com.neteast.framework.websockt.bean.Custom;
import com.neteast.framework.websockt.bean.OperaRecord;
import com.neteast.framework.websockt.handler.SocketIOMessageEventHandler;
import com.neteast.framework.websockt.service.SocketIOService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
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

    private ExpertOperaRecordService expertOperaRecordService;

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
            //获取接收方
            List<Custom> customs = SocketIOMessageEventHandler.getSocketIOByRole(receiver,channel);
            //添加记录操作
            SocketIOMessageEventHandler.setOperaRecord(operaRecord.getChannel(),operaRecord);
            //获取返回体
            String body = messageHandler(receiver,operaRecord);
            customs.forEach(o->{
                //实时操作状态
                SocketIOClient socketIOClient = socketIOServer.getClient(o.getUuid());
                SocketIOService.sendMsg(o, body,socketIOClient);
            });
        }
    }

    private String messageHandler(String receiver,OperaRecord operaRecord){

        String temp = "";
        switch (receiver){
            //发送主持人端信息
            case "client":
                //设置专家的评选信息
                ExpertBidMsg expertBidMsg = setExpertSelectRecord(operaRecord);
                CompletionStatus completionStatus = getCompletionStatus(expertBidMsg,operaRecord.getItemType());
                setSupplierBidRecord(operaRecord,completionStatus);
                break;
            //发送专家端
            case "user":
                temp = operaRecord.getRecord();
        }
        return temp;
    }

    /**
     * @Description 设置专家端的的数据
     * @author lzp
     * @Date 2023/12/27
     */
    private ExpertBidMsg setExpertSelectRecord(OperaRecord operaRecord){

        String channel = operaRecord.getChannel();
        List<ExpertBidMsg> list = map.get(channel);
        if (list != null){
            for (ExpertBidMsg expertBidMsg:list) {
                if (expertBidMsg.getId()==operaRecord.getUserId()&&
                        expertBidMsg.getSupplierId()==operaRecord.getSupplierId()&&
                        expertBidMsg.getPackageId()==operaRecord.getPackageId()){
                    //更新专家的信息
                    updateExpertBidMsg(expertBidMsg,operaRecord);
                    return expertBidMsg;
                }
            }
        }else {
            list = new ArrayList<>();
        }
        ExpertBidMsg expertBidMsg = new ExpertBidMsg();
        //设置专家的信息
        addExpertBidMsg(expertBidMsg,operaRecord);
        list.add(expertBidMsg);
        map.put(channel,list);
        return expertBidMsg;
    }

    private void setSupplierBidRecord(OperaRecord operaRecord,CompletionStatus completionStatus){

        String channel = operaRecord.getChannel();
        List<SupplierBidMsg> list = supplierMap.get(channel);
        if (list != null){
            for (SupplierBidMsg supplierBidMsg:list){
                if (supplierBidMsg.getSupplierId()==operaRecord.getSupplierId()){
                    supplierBidMsg.addReviewStatus(completionStatus,operaRecord.getItemType());
                    setSupplierConsistent(supplierBidMsg,operaRecord);
                    return;
                }
            }
        }else {
            list = new ArrayList<>();
        }
        SupplierBidMsg supplierBidMsg = new SupplierBidMsg();
        supplierBidMsg.setSupplierId(operaRecord.getSupplierId());
        supplierBidMsg.setSupplierName(supplierBidMsg.getSupplierName());
        supplierBidMsg.setPackageId(supplierBidMsg.getPackageId());
        supplierBidMsg.addReviewStatus(completionStatus,operaRecord.getItemType());

        setSupplierConsistent(supplierBidMsg,operaRecord);
        list.add(supplierBidMsg);
        supplierMap.put(channel,list);
    }

    private CompletionStatus getCompletionStatus(ExpertBidMsg expertBidMsg,String itemType){
        CompletionStatus status = new CompletionStatus();
        status.setSupplierId(expertBidMsg.getSupplierId());
        status.setName(expertBidMsg.getName());
        status.setUserId(expertBidMsg.getId());
        int num = 0;
        switch (itemType){
            //资格审查
            case "qualification":
                num = expertBidMsg.getQualificationScore().getNum();
                status.setNum(num);
                break;
            //商务分
            case "business":
                num = expertBidMsg.getBusinessScore().getNum();
                status.setNum(num);
                break;
            //符合性
            case "conform":
                num = expertBidMsg.getConformScore().getNum();
                status.setNum(num);
                break;
            //技术分
            case "technical":
                num = expertBidMsg.getTechScore().getNum();
                status.setNum(num);
                break;
            default:
                status.setNum(num);
        }
        return status;
    }

    /**
     * @Description 更新专家信息
     * @author lzp
     * @Date 2023/12/27
     */
    private void updateExpertBidMsg(ExpertBidMsg expertBidMsg,OperaRecord operaRecord){

        String itemType = operaRecord.getItemType();
        String opera = operaRecord.getRecord();
        switch (itemType){
            //资格审查
            case "qualification":
                RadioItem radioItem = JSONObject.parseObject(opera,RadioItem.class);
                expertBidMsg.getQualificationScore().setRadioItem(radioItem);
                break;
            //商务分
            case "business":
                GradeItem businessItem = JSONObject.parseObject(opera,GradeItem.class);
                expertBidMsg.getBusinessScore().setGradeItems(businessItem);
                break;
            //符合性
            case "conform":
                SelectItem selectItem = JSONObject.parseObject(opera,SelectItem.class);
                expertBidMsg.getConformScore().setSelectItem(selectItem);
                break;
            //技术分
            case "technical":
                GradeItem techItem = JSONObject.parseObject(opera,GradeItem.class);
                expertBidMsg.getTechScore().setGradeItems(techItem);
                break;
            //价格分
            case "price":
                PriceScore priceScore = JSONObject.parseObject(opera,PriceScore.class);
                expertBidMsg.setPriceScore(priceScore);
                break;
        }
    }

    /**
     * @Description 增加专家信息
     * @author lzp
     * @Date 2023/12/27
     */
    private void addExpertBidMsg(ExpertBidMsg expertBidMsg,OperaRecord operaRecord){

        expertBidMsg.setId(operaRecord.getUserId());
        expertBidMsg.setName(operaRecord.getUserName());
        expertBidMsg.setSupplierId(operaRecord.getSupplierId());
        expertBidMsg.setPackageId(operaRecord.getPackageId());
        updateExpertBidMsg(expertBidMsg,operaRecord);
    }

    /**
     * @Description 设置供应商的的 一致性和是否淘汰情况
     * @author lzp
     * @Date 2023/12/27
     */
    private void setSupplierConsistent(SupplierBidMsg supplierBidMsg,OperaRecord operaRecord){
        String itemType = operaRecord.getItemType();
        String opera = operaRecord.getRecord();
        switch (itemType){
            //资格审查
            case "qualification":
                RadioItem radioItem = JSONObject.parseObject(opera,RadioItem.class);
                if (radioItem.getTitleType()==2){
                    supplierBidMsg.addQualificationConsistent(radioItem);
                }
                if (radioItem.getPassType()==1){
                    supplierBidMsg.addOutNumStatus(radioItem);
                }
                break;
            //商务分
            case "business":
                GradeItem businessItem = JSONObject.parseObject(opera,GradeItem.class);
                if (businessItem.getTitleType()==2){
                    supplierBidMsg.addBusinessConsistent(businessItem);
                }
                break;
            //符合性
            case "conform":
                SelectItem selectItem = JSONObject.parseObject(opera,SelectItem.class);
                if (selectItem.getTitleType()==2){
                    supplierBidMsg.addConformConsistent(selectItem);
                }
                if (selectItem.getPassType()==1){
                    supplierBidMsg.addOutNumStatus(selectItem);
                }
                break;
            //技术分
            case "technical":
                GradeItem techItem = JSONObject.parseObject(opera,GradeItem.class);
                if (techItem.getTitleType()==2){
                    supplierBidMsg.addTechConsistent(techItem);
                }
                break;
        }
    }


}
