package com.neteast.web.controller.ws;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.neteast.business.domain.bid.ExpertBidMsg;
import com.neteast.business.domain.bid.SupplierBidMsg;
import com.neteast.business.domain.project.SupplierInformation;
import com.neteast.business.service.ISupplierInformationService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.framework.websockt.service.SocketIOService;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSInput;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    ISupplierInformationService supplierInformationService;

    @GetMapping("/channel")
    public AjaxResult getWsOneChannel(String channelName) {
        logger.info("创建通道-{}",channelName);
        socketIOServer.addEventListener(channelName, String.class, new SocketIOListener(socketIOServer));
        return success();
    }

    /**
     * @Description 专家端展示
     * @author lzp
     * @Date 2023/12/11
     */
    @GetMapping("/expertSelect")
    public AjaxResult getExpertSelectMsg(String channel,int userId,int packageId){
        List<ExpertBidMsg> list = SocketIOListener.map.get(channel);
        List<ExpertBidMsg> res = list.stream().filter(u->u.getId()==userId).filter(u->u.getPackageId()==packageId).collect(Collectors.toList());
        return success(res);
    }

    /**
     * @Description 主持人端展示
     * @author lzp
     * @Date 2023/12/11
     */
    @GetMapping("/projectShow")
    public AjaxResult getProjectShow(String channel,Integer projectId,Integer packageId){
        List<ExpertBidMsg> list = SocketIOListener.map.get(channel);
        Map<Integer,List<ExpertBidMsg>> map = list.stream().collect(Collectors.groupingBy(ExpertBidMsg::getSupplierId));
        List<SupplierInformation> information = supplierInformationService.getList(projectId,packageId);
        List<SupplierBidMsg> msgList = new ArrayList<>();
        information.forEach(i->{
            SupplierBidMsg supplierBidMsg = new SupplierBidMsg();
            supplierBidMsg.setProjectId(i.getProjectId());
            supplierBidMsg.setSupplierId(i.getId());
            supplierBidMsg.setSupplierName(i.getName());
            supplierBidMsg.setExpertBidMsg(map.get(i.getId()));
            msgList.add(supplierBidMsg);
        });
        return success(msgList);
    }


}
