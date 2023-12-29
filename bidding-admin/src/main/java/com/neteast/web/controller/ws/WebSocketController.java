package com.neteast.web.controller.ws;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.neteast.business.domain.bid.*;
import com.neteast.business.domain.bid.score.GradeItem;
import com.neteast.business.domain.bid.score.PriceScore;
import com.neteast.business.domain.bid.score.RadioItem;
import com.neteast.business.domain.bid.score.SelectItem;
import com.neteast.business.domain.project.*;
import com.neteast.business.domain.project.vo.ExpertSubmitVO;
import com.neteast.business.domain.project.vo.ProjectExpertVO;
import com.neteast.business.service.*;
import com.neteast.business.service.impl.SupplierInformationServiceImpl;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.framework.websockt.bean.Custom;
import com.neteast.framework.websockt.handler.SocketIOMessageEventHandler;
import com.neteast.framework.websockt.service.SocketIOService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSInput;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lzp
 * @date 2023年11月28 17:19
 */

@RestController
@RequestMapping("/webSocket")
public class WebSocketController extends BaseController {

    @Resource
    IPackageInformationService packageInformationService;

    @Resource
    IScoreMethodService scoreMethodService;

    @Resource
    ISupplierInformationService supplierInformationService;

    @Resource
    IProjectExpertService projectExpertService;

    @Resource
    SocketIOServer socketIOServer;

    @Resource
    IExpertOperaRecordService operaRecordService;

    @Resource
    ICompletionStatusService statusService;

    /**
     * @Description 专家端获取其操作的信息,以及主持人查看专家评审详情
     * userId: 专家id
     * packageId: 分包id
     * itemId: 评分项id
     * @author lzp
     * @Date 2023/12/11
     */
    @GetMapping("/expertSelect")
    public AjaxResult getExpertSelectMsg(Integer userId, Integer supplierId, Integer itemId,String itemType){

        List<ExpertOperaRecord> list = operaRecordService.getExpertOperaRecordByItemId(supplierId,userId,itemId);
        switch (itemType){
            case "qualification":
                List<RadioItem> radioItems = new ArrayList<>();
                list.forEach(l->{
                    RadioItem item = ExpertOperaRecord.toRadioItem(l);
                    radioItems.add(item);
                });
                return success(radioItems);
            case "business":
            case "technical":
                List<GradeItem> gradeItems = new ArrayList<>();
                list.forEach(l->{
                    GradeItem item = ExpertOperaRecord.toGradItem(l);
                    gradeItems.add(item);
                });
                return success(gradeItems);
            case "conform":
                List<SelectItem> selectItems = new ArrayList<>();
                list.forEach(l->{
                    SelectItem item = ExpertOperaRecord.toSelectItem(l);
                    selectItems.add(item);
                });
                return success(selectItems);
            case "price":
                List<PriceScore> priceScores = new ArrayList<>();
                list.forEach(l->{
                    PriceScore score = ExpertOperaRecord.toPriceScore(l);
                    priceScores.add(score);
                });
                return success(priceScores);
        }
        return error("无该评分项类型");
    }

    /**
     * @Description 主持人端展示数据
     * @author lzp
     * @Date 2023/12/11
     */
    @GetMapping("/projectShow")
    public AjaxResult getProjectShow(Integer packageId){

        List<CompletionStatus> list = statusService.getListByPackageId(packageId);
        return success(list);
    }

    /**
     * @Description 查看做题情况
     * @author lzp
     * @Date 2023/12/14
     */
    @GetMapping("/getWholeCompletionStatus")
    public AjaxResult getWholeCompletionStatus(Integer packageId){

        Long count = statusService.lambdaQuery().eq(CompletionStatus::getPackageId,packageId).count();
        return success(count);
    }

    /**
     * @Description 专家提交评审阶段信息
     * @author lzp
     * @Date 2023/12/28
     */
    @PostMapping("/submitMsg")
    public AjaxResult expertToNextStep(ExpertSubmitVO expertSubmitVO){
        //更新专家的阶段状态
        projectExpertService.updateExpertStepStatus(expertSubmitVO);
        //判断在当前状态的专家数
        Long num = projectExpertService.lambdaQuery().eq(ProjectExpert::getProjectId,expertSubmitVO.getProjectId())
                .eq(ProjectExpert::getPackageId,expertSubmitVO.getProjectId())
                .eq(ProjectExpert::getStepId,expertSubmitVO.getStepId()).count();
        //该分包的评审人数
        Long total = projectExpertService.lambdaQuery().eq(ProjectExpert::getProjectId,expertSubmitVO.getProjectId())
                .eq(ProjectExpert::getPackageId,expertSubmitVO.getPackageId()).count();
        //更新解锁下一个阶段
        if (total.compareTo(num)==0){
            List<Custom> list = SocketIOMessageEventHandler.getSocketIOByChannel(expertSubmitVO.getChannel());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("next","true");
            list.forEach(l->{
                SocketIOClient client = socketIOServer.getClient(l.getUuid());
                SocketIOService.sendMsg(l,jsonObject.toString(),client);
            });
        }
        return success();
    }


    /**
     * @Description 专家每个分包的价格分计算,获取价格分
     * @author lzp
     * @Date 2023/12/26
     */
    @PostMapping("/getPriceScore")
    public AjaxResult getSupplierPriceScore(Integer packageId,Integer supplierId){

        PackageInformation information = packageInformationService.getById(packageId);
        SupplierInformation supplierInformation = supplierInformationService.getById(supplierId);
        //获取评分方式
        Integer scoreId = information.getScoreId();
        //通过评分方式进行公式计算
        ScoreMethod method = scoreMethodService.getById(scoreId);
        Double res = getPriceScoreByScoreMethod(supplierInformation,method,information);
        return success(res);
    }

    /**
     * @Description 计算供应商得分 (综合评分法,最低得分法)
     * @author lzp
     * @Date 2023/12/26
     */
    private Double getPriceScoreByScoreMethod(SupplierInformation supplierInformation, ScoreMethod method,PackageInformation packageInformation){
        return 0.0D;
    }

}
