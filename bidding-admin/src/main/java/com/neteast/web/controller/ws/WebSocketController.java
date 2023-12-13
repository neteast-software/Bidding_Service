package com.neteast.web.controller.ws;

import com.alibaba.fastjson2.JSON;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.neteast.business.domain.bid.*;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.SupplierInformation;
import com.neteast.business.service.IProjectScoreItemService;
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

    @Resource
    IProjectScoreItemService projectScoreItemService;

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
     * @Description 主持人端展示数据
     * @author lzp
     * @Date 2023/12/11
     */
    @GetMapping("/projectShow")
    public AjaxResult getProjectShow(String channel,Integer projectId,Integer packageId){
        List<ExpertBidMsg> list = SocketIOListener.map.get(channel);
        Map<Integer,List<ExpertBidMsg>> map = list.stream().collect(Collectors.groupingBy(ExpertBidMsg::getSupplierId));
        List<SupplierInformation> information = supplierInformationService.getList(projectId,packageId);
        List<ProjectScoreItem> projectScoreItems = projectScoreItemService.getProjectScoreItemList(projectId,packageId);
        List<SupplierBidMsg> msgList = new ArrayList<>();
        information.forEach(i->{
            projectScoreItems.forEach(p->{
                SupplierBidMsg supplierBidMsg = new SupplierBidMsg();
                supplierBidMsg.setProjectId(i.getProjectId());
                supplierBidMsg.setSupplierId(i.getId());
                supplierBidMsg.setSupplierName(i.getName());
                TotalScore totalScore = getTotalScore(map.get(i.getId()),p);
                supplierBidMsg.setTotalScores(totalScore);
                msgList.add(supplierBidMsg);
            });
        });
        return success(msgList);
    }

    private TotalScore getTotalScore(List<ExpertBidMsg> expertBidMsg,ProjectScoreItem projectScoreItem){
        TotalScore totalScore = new TotalScore();
        totalScore.setNum(projectScoreItem.getNum());
        totalScore.setType(projectScoreItem.getValueType());
        totalScore.setItemType(projectScoreItem.getItemType());
        totalScore.setExpertNum(expertBidMsg.size());
        List<CompletionStatus> completionStatuses = new ArrayList<>();
        expertBidMsg.forEach(e->{
            CompletionStatus completionStatus = new CompletionStatus();
            completionStatus.setName(e.getName());
            completionStatus.setUserId(e.getId());
            completionStatus.setNum(e.getReviewStatus().size());
            if (projectScoreItem.getValueType()==1){
                completionStatus.setPass(e.getPass());
            }else {
                completionStatus.setValue(e.getValue());
            }
            completionStatuses.add(completionStatus);
        });
        totalScore.setCompletionStatuses(completionStatuses);
        return totalScore;
    }


}
