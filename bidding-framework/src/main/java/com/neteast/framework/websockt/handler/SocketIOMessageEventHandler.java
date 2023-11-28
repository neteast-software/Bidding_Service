package com.neteast.framework.websockt.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lzp
 * @date 2023年11月28 15:15
 */

@Slf4j
@Data
@Component
public class SocketIOMessageEventHandler{

    private static ConcurrentHashMap<String,SocketIOClient> clientHashMap = new ConcurrentHashMap<>();

    public static SocketIOClient getSocketIOClient(String key){
        return clientHashMap.get(key);
    }

    private SocketIOServer socketIOServer;

    @Autowired
    public void setSocketIOServer(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

    @PostConstruct
    public void startUp(){
        log.info("webSocket服务启动...");
        socketIOServer.start();
    }

    @PreDestroy
    public void stopUp(){
        log.info("webSocket服务关闭...");
        socketIOServer.stop();
    }

    @OnConnect
    public void onConnect(SocketIOClient client){
        String sessionId = client.getSessionId().toString().replace("-","");
        clientHashMap.put(sessionId,client);
        String channel = client.getHandshakeData().getSingleUrlParam("channel");
        if (channel!=null){
            client.sendEvent(channel,sessionId);
        }else {
            client.sendEvent("default",sessionId);
        }
        log.info("客户端-{},session-{}",client.getRemoteAddress(),client.getSessionId());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client){
        log.info("客户端-{},session-{},断开连接",client.getRemoteAddress(),client.getSessionId());
        String sessionId = client.getSessionId().toString().replace("-","");
        clientHashMap.remove(sessionId);
        // TODO: 提醒观察者
    }

}
