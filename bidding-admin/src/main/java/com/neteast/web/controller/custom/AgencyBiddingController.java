package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.AgentMessage;
import com.neteast.business.service.IAgencyBiddingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.common.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 招标代理商信息
 * @author lzp
 * @date 2023年11月15 11:47
 */

@RestController
@RequestMapping("/agencyBidding")
public class AgencyBiddingController extends BaseController {

    @Resource
    IAgencyBiddingService agencyBiddingService;

    @GetMapping("/list")
    public AjaxResult getAgencyBiddingList(AgentMessage agentMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<AgentMessage> list = agencyBiddingService.getAgencyBiddingData(agentMessage);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addAgencyBiddingData(@RequestBody AgentMessage agentMessage){
        agentMessage.setCreateTime(new Date());
        agentMessage.setCreateBy(SecurityUtils.getUsername());
        agencyBiddingService.save(agentMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delAgencyBiddingData(@PathVariable Integer id){
        agencyBiddingService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateAgencyBiddingData(@RequestBody AgentMessage agentMessage){
        agentMessage.setUpdateTime(new Date());
        agentMessage.setUpdateBy(SecurityUtils.getUsername());
        agencyBiddingService.updateById(agentMessage);
        return success();
    }
}
