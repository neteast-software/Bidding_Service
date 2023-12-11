package com.neteast.web.controller.ws;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.framework.websockt.listner.SocketIOListener;
import com.neteast.framework.websockt.service.SocketIOService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author lzp
 * @date 2023年11月28 17:19
 */

@RestController
@RequestMapping("/webSocket")
public class WebSocketController extends BaseController {

    @Resource
    SocketIOServer socketIOServer;

    @GetMapping("/channel")
    public AjaxResult getWsOneChannel(String channelName) {
        logger.info("创建通道-{}",channelName);
        socketIOServer.addEventListener(channelName, String.class, new SocketIOListener(socketIOServer));
        return success();
    }
}
