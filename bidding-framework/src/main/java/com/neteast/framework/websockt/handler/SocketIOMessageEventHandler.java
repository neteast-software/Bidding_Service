package com.neteast.framework.websockt.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.neteast.framework.websockt.bean.Custom;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lzp
 * @date 2023年11月28 15:15
 */

@Slf4j
@Data
@Component
public class SocketIOMessageEventHandler{

    private static ConcurrentHashMap<String,Custom> clientHashMap = new ConcurrentHashMap<>();

    public static Custom getSocketIOClient(String key){
        return clientHashMap.get(key);
    }

    public static List<Custom> getSocketIOByRole(String role){

        List<Custom> customs = new ArrayList<>();
        clientHashMap.forEach((k,y)->{
            if (role.equals(y.getRole())){
                customs.add(y);
            }
        });
        return customs;
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
        String channel = client.getHandshakeData().getSingleUrlParam("channel");
        String role = client.getHandshakeData().getSingleUrlParam("role");
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        if (channel!=null&&role!=null){
            Custom custom = new Custom();
            custom.setSessionId(sessionId);
            custom.setChannel(channel);
            custom.setRole(role);
            custom.setSocketIOClient(client);
            clientHashMap.put(sessionId,custom);
            client.sendEvent(channel,sessionId);
        }else {
            client.sendEvent("default",false);
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
