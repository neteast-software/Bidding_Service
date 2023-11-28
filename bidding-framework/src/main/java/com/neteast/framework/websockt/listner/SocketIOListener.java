package com.neteast.framework.websockt.listner;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lzp
 * @date 2023年11月28 17:43
 */

@Slf4j
public class SocketIOListener implements DataListener<String> {

    @Override
    public void onData(SocketIOClient client, String s, AckRequest ackRequest) throws Exception {
        log.info("客户端-{},发送消息-{}",client.getRemoteAddress(),s);
    }
}
