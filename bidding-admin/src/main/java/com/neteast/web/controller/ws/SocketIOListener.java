package com.neteast.web.controller.ws;

import com.alibaba.fastjson2.JSON;
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
import com.neteast.business.domain.project.ExpertOperaRecord;
import com.neteast.business.service.IExpertOperaRecordService;
import com.neteast.framework.websockt.bean.Custom;
import com.neteast.framework.websockt.bean.OperaRecord;
import com.neteast.framework.websockt.handler.SocketIOMessageEventHandler;
import com.neteast.framework.websockt.service.SocketIOService;
import io.swagger.models.auth.In;
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

    private IExpertOperaRecordService recordService;

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
                //保存专家信息
                CompletionStatus completionStatus = updateExpertBidMsg(operaRecord);
                //对完成信息进行保存/更新

                CompletionMsg completionMsg = convertCompletionMsg(completionStatus,operaRecord);
                setSupplierConsistent(operaRecord,completionMsg);
                temp = JSON.toJSONString(completionMsg);
                break;
            //发送专家端
            case "user":
                temp = operaRecord.getRecord();
        }
        return temp;
    }

    private CompletionMsg convertCompletionMsg(CompletionStatus completionStatus,OperaRecord operaRecord){

        CompletionMsg completionMsg = new CompletionMsg();
        completionMsg.setSupplierId(operaRecord.getSupplierId());
        completionMsg.setUserId(operaRecord.getUserId());
        completionMsg.setItemId(operaRecord.getItemId());
        completionMsg.setItemType(operaRecord.getItemType());
        completionMsg.setNum(completionStatus.getNum());
        return completionMsg;
    }

    /**
     * @Description 更新专家信息
     * @author lzp
     * @Date 2023/12/27
     */
    private CompletionStatus updateExpertBidMsg(OperaRecord operaRecord){

        String itemType = operaRecord.getItemType();
        String opera = operaRecord.getRecord();
        //操作记录保留
        ExpertOperaRecord record = new ExpertOperaRecord();
        record.setExpertId(operaRecord.getUserId());
        record.setSupplierId(operaRecord.getSupplierId());
        record.setScoreItemId(operaRecord.getItemId());
        record.setItemType(operaRecord.getItemType());
        //返回主持人端数据信息
        CompletionStatus completionStatus = new CompletionStatus();
        completionStatus.setSupplierId(operaRecord.getSupplierId());
        completionStatus.setUserId(operaRecord.getUserId());
        completionStatus.setItemId(operaRecord.getItemId());
        completionStatus.setName(operaRecord.getUserName());
        Integer item = -1;
        switch (itemType){
            //资格审查
            case "qualification":
                RadioItem radioItem = JSONObject.parseObject(opera,RadioItem.class);
                item = radioItem.getId();
                record.setPassType(radioItem.getPassType());
                record.setTitleType(radioItem.getTitleType());
                record.setInputType(radioItem.getInputType());//单选
                record.setChoose(radioItem.getChoose());
                break;
            //商务分
            case "business":
            //技术分
            case "technical":
                GradeItem businessItem = JSONObject.parseObject(opera,GradeItem.class);
                item = businessItem.getId();
                record.setPassType(businessItem.getPassType());
                record.setTitleType(businessItem.getTitleType());
                record.setInputType(businessItem.getInputType());
                record.setValue(businessItem.getValue());
                break;
            //符合性
            case "conform":
                SelectItem selectItem = JSONObject.parseObject(opera,SelectItem.class);
                item = selectItem.getId();
                record.setPassType(selectItem.getPassType());
                record.setTitleType(selectItem.getTitleType());
                record.setInputType(selectItem.getInputType());
                record.setValue(selectItem.getValue());
                record.setChoose(selectItem.getChoose());
                break;
            //价格分
            case "price":
                PriceScore priceScore = JSONObject.parseObject(opera,PriceScore.class);
                record.setValue(priceScore.getValue());
                record.setPassType(3);// 输入分值
                record.setInputType(2);// 分数
                record.setTitleType(1);// 主观/客观题
                break;
        }
        record.setItemId(item);
        //对专家记录进行保存或更新
        Long count = recordService.lambdaQuery().eq(ExpertOperaRecord::getItemId,record.getItemId())
                .eq(ExpertOperaRecord::getExpertId,record.getExpertId()).count();
        if (count==0){
            recordService.save(record);
        }else {
            recordService.updateRecordByItemId(record);
        }

        //查看该专家该供应商该评分项的完成情况
        if ("price".equals(itemType)){
            //价格分展示
            completionStatus.setScore(record.getValue());
        }else {
            //其他评分项展示
            Long num = recordService.getCountByCompletionStatus(completionStatus);
            completionStatus.setNum(num);
        }
        return completionStatus;
    }

    /**
     * @Description 设置供应商的的 一致性和是否淘汰情况
     * @author lzp
     * @Date 2023/12/27
     */
    private void setSupplierConsistent(OperaRecord operaRecord,CompletionMsg completionMsg){

        //获取评分项类型
        String itemType = operaRecord.getItemType();

        //判断供应商该评分项的一致性信息
        boolean consistent = recordService.getConsistentBySupplierId(operaRecord.getItemId(),operaRecord.getSupplierId());
        completionMsg.setConsistent(consistent);

        if ("conform".equals(itemType)||"qualification".equals(itemType)){
            //判断供应商该评分项的淘汰
            boolean out = recordService.getOutBySupplierId(operaRecord.getPackageId(),operaRecord.getSupplierId(),operaRecord.getItemId());
            completionMsg.setOut(out);
        }else {
            completionMsg.setOut(false);
        }
    }

}
