package com.neteast.framework.websockt.handler;

import com.alibaba.fastjson2.JSONObject;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.neteast.framework.websockt.bean.Custom;
import com.neteast.framework.websockt.bean.OperaRecord;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lzp
 * @date 2023年11月28 15:15
 */

@Slf4j
@Data
@Component
public class SocketIOMessageEventHandler{

    /** 登录用户信息 */
    private static ConcurrentHashMap<UUID,Custom> clientHashMap = new ConcurrentHashMap<>();

    /** 在会议确定结束时清除 */
    private static ConcurrentHashMap<String, List<OperaRecord>> operaHashMap = new ConcurrentHashMap<>();

    public static void setOperaRecord(String channelId,OperaRecord operaRecord){
        List<OperaRecord> list = operaHashMap.get(channelId);
        if (list==null){
            List<OperaRecord> operaRecords = new ArrayList<>();
            operaRecords.add(operaRecord);
            operaHashMap.put(channelId,operaRecords);
        }else {
            list.add(operaRecord);
        }
    }

    public static List<OperaRecord> getPeraRecord(String channelId,String role){
        List<OperaRecord> list = new ArrayList<>();
        List<OperaRecord> temp = operaHashMap.get(channelId);
        if (temp!=null){
            temp.forEach(t->{
                if (role.equals(t.getReceiver())){
                    list.add(t);
                }
            });
        }
        return list;
    }

    public static void clearOperaRecord(String channelId){
        operaHashMap.remove(channelId);
        log.info("会议通道{}的会议记录删除",channelId);
    }

    public static Custom getSocketIOClient(String key){
        return clientHashMap.get(key);
    }

    public static List<Custom> getSocketIOByRole(String role,String channel){

        List<Custom> customs = new ArrayList<>();
        clientHashMap.forEach((k,y)->{
            if (channel.equals(y.getChannel())){
                if (role.equals(y.getRole())){
                    customs.add(y);
                }
            }
        });
        return customs;
    }

    public static List<Custom> getSocketIOByChannel(String channel){
        List<Custom> customs = new ArrayList<>();
        clientHashMap.forEach((k,y)->{
            if (channel.equals(y.getChannel())){
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

        String channel = client.getHandshakeData().getSingleUrlParam("channel");
        String role = client.getHandshakeData().getSingleUrlParam("role");
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        if (channel!=null&&role!=null){
            Custom custom = new Custom();
            custom.setUuid(client.getSessionId());
            custom.setChannel(channel);
            custom.setRole(role);
            custom.setUserId(userId);
            custom.setStatus("用户加入");
            clientHashMap.put(client.getSessionId(),custom);
            //client.sendEvent(channel,userId);
            //当前通道的操作记录
            List<OperaRecord> list = getPeraRecord(channel,role);
            list.forEach(o->{
                String body = JSONObject.toJSONString(o);
                client.sendEvent(channel,body);
            });
            //通知用户登录
            List<Custom> customs = getSocketIOByChannel(channel);
            customs.forEach(c->{
                SocketIOClient socketIOClient = socketIOServer.getClient(c.getUuid());
                socketIOClient.sendEvent(channel,custom.toString());
            });
        }else {
            client.sendEvent("default",false);
        }
        log.info("客户端-{},session-{}",client.getRemoteAddress(),client.getSessionId());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client){
        log.info("客户端-{},session-{},断开连接",client.getRemoteAddress(),client.getSessionId());
        Custom custom = clientHashMap.remove(client.getSessionId());
        custom.setStatus("用户离开");
        String channel = custom.getChannel();
        List<Custom> customs = getSocketIOByChannel(channel);
        customs.forEach(c->{
            SocketIOClient socketIOClient = socketIOServer.getClient(c.getUuid());
            socketIOClient.sendEvent(channel,custom.toString());
        });
    }

}
