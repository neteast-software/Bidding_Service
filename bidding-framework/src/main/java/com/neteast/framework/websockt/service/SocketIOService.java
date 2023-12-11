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

@Slf4j
public class SocketIOService {

    public static void sendMsg(String key,String channel,String body,SocketIOClient socketIOClient){
        log.info("发送前端消息：用户-{},channel-{},body-{}",key,channel,body);
        Custom custom = SocketIOMessageEventHandler.getSocketIOClient(key);
        if (custom!=null){
            socketIOClient.sendEvent(channel,body);
        }else {
            log.info("接收用户已离线-{},通道-{},请求体内容为-{}",key,channel,body);
        }
    }

    public static void sendMsg(Custom custom,String body,SocketIOClient socketIOClient){
        log.info("发送前端消息：用户-{},channel-{},body-{}",custom.getUserId(),custom.getChannel(),body);
        socketIOClient.sendEvent(custom.getChannel(),body);
    }


}
