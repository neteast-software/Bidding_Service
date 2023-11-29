package com.neteast.framework.websockt.bean;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月29 17:43
 */

@Data
public class Custom {

    String role;

    String sessionId;

    String channel;

    SocketIOClient socketIOClient;

}
