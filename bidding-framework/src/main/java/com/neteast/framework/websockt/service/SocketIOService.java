package com.neteast.framework.websockt.service;

import com.corundumstudio.socketio.SocketIOClient;
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
        log.info("发送前端消息：key-{},channel-{},body-{}",key,channel,body);
        SocketIOClient socketIOClient = SocketIOMessageEventHandler.getSocketIOClient(key);
        socketIOClient.sendEvent(channel,body);
    }
}
