package com.neteast.web.controller.ws;

import com.corundumstudio.socketio.SocketIOServer;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.framework.websockt.listner.SocketIOListener;
import com.neteast.framework.websockt.service.SocketIOService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lzp
 * @date 2023年11月28 17:19
 */

@RestController
@RequestMapping("/webSocket")
public class WebSocketController extends BaseController {

    @Resource
    SocketIOServer socketIOServer;

    @Resource
    SocketIOService socketIOService;

    @GetMapping("/channel")
    public AjaxResult getWsOneChannel(String channelName) {
        socketIOServer.addEventListener(channelName, String.class, new SocketIOListener());
        return success();
    }

}
