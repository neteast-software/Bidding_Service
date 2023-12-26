package com.neteast.web.controller.ws;

import com.alibaba.fastjson2.JSON;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.neteast.business.domain.bid.*;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreMethod;
import com.neteast.business.domain.project.SupplierInformation;
import com.neteast.business.service.IPackageInformationService;
import com.neteast.business.service.IProjectScoreItemService;
import com.neteast.business.service.IScoreMethodService;
import com.neteast.business.service.ISupplierInformationService;
import com.neteast.business.service.impl.SupplierInformationServiceImpl;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.framework.websockt.service.SocketIOService;
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
    public AjaxResult getProjectShow(String channel,Integer packageId){

        List<SupplierBidMsg> list = SocketIOListener.supplierMap.get(channel);
        List<SupplierBidMsg> res = list.stream().filter(u->u.getPackageId().intValue()==packageId).collect(Collectors.toList());
        return success(res);
    }

    /**
     * @Description 查看做题情况
     * @author lzp
     * @Date 2023/12/14
     */
    @GetMapping("/getWholeCompletionStatus")
    public AjaxResult getWholeCompletionStatus(String channel,Integer packageId){
        List<SupplierBidMsg> list = SocketIOListener.supplierMap.get(channel);
        List<SupplierBidMsg> temp = list.stream().filter(u->u.getPackageId().intValue()==packageId).collect(Collectors.toList());
        Long res = temp.stream().collect(Collectors.summarizingInt(SupplierBidMsg::getNum)).getSum();
        return success(res);
    }

    /**
     * @Description 获取某个供应商的评分详情
     * @author lzp
     * @Date 2023/12/20
     */
    @GetMapping("/getSupplierDetail")
    public AjaxResult getSupplierDetail(String channel,Integer packageId,Integer supplierId){
        List<SupplierBidMsg> list = SocketIOListener.supplierMap.get(channel);
        List<SupplierBidMsg> temp = list.stream().filter(u->u.getPackageId().intValue()==packageId).
                filter(t->t.getSupplierId().intValue()==supplierId).collect(Collectors.toList());
        if (temp.size()==1){
            return success(temp);
        }
        return error("获取供应商详情信息出错");
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
