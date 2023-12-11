package com.neteast.framework.websockt.listner;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.neteast.framework.websockt.bean.Custom;
import com.neteast.framework.websockt.bean.OperaRecord;
import com.neteast.framework.websockt.handler.SocketIOMessageEventHandler;
import com.neteast.framework.websockt.service.SocketIOService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月28 17:43
 */

@Slf4j
public class SocketIOListener implements DataListener<String> {

    @Override
    public void onData(SocketIOClient client, String s, AckRequest ackRequest) throws Exception {
        log.info("客户端-{},发送消息-{}",client.getRemoteAddress(),s);
        //String sessionId = client.getSessionId().toString().replace("-","");
        //Custom custom = SocketIOMessageEventHandler.getSocketIOClient(sessionId);
        //JSONObject jsonObject = JSON.parseObject(s);
        //String receiver = jsonObject.getString("receiver");
        OperaRecord operaRecord = JSONObject.parseObject(s,OperaRecord.class);
        String receiver = operaRecord.getReceiver();
        String channel = operaRecord.getChannel();
        List<Custom> customs = SocketIOMessageEventHandler.getSocketIOByRole(receiver,channel);
        //记录操作
        SocketIOMessageEventHandler.setOperaRecord(operaRecord.getChannel(),operaRecord);
        customs.forEach(o->{
            //实时操作状态
            String body = JSON.toJSONString(operaRecord);
            SocketIOService.sendMsg(o,body,client);
        });
    }
}
