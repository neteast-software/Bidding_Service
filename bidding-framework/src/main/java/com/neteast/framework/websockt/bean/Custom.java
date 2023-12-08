package com.neteast.framework.websockt.bean;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.Data;

/**
 * 客户端用户
 * @author lzp
 * @date 2023年11月29 17:43
 */

@Data
public class Custom {

    /** 用户角色 */
    String role;

    /** sessionId */
    String sessionId;

    /** 通道 */
    String channel;

    /** socket客户端 */
    SocketIOClient socketIOClient;

}
