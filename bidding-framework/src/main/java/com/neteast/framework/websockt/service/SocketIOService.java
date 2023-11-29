package com.neteast.framework.websockt.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.neteast.common.exception.BaseBusException;
import com.neteast.framework.websockt.bean.Custom;
import com.neteast.framework.websockt.handler.SocketIOMessageEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lzp
 * @date 2023年11月28 17:37
 */

@Component
@Slf4j
public class SocketIOService {

    public void sendMsg(String key,String channel,String body){
        log.info("发送前端消息：用户-{},channel-{},body-{}",key,channel,body);
        Custom custom = SocketIOMessageEventHandler.getSocketIOClient(key);
        if (custom!=null){
            SocketIOClient socketIOClient = custom.getSocketIOClient();
            socketIOClient.sendEvent(channel,body);
        }else {
            log.info("接收用户已离线-{},通道-{},请求体内容为-{}",key,channel,body);
        }
    }

    public void sendMsg(Custom custom,String body){
        log.info("发送前端消息：用户-{},channel-{},body-{}",custom.getSessionId(),custom.getChannel(),body);
        if (custom!=null){
            SocketIOClient socketIOClient = custom.getSocketIOClient();
            socketIOClient.sendEvent(custom.getChannel(),body);
        }else {
            log.info("接收用户已离线-{},通道-{},请求体内容为-{}",custom.getSessionId(),custom.getChannel(),body);
        }
    }
}
