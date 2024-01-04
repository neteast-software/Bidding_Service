package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.AgencyMessage;
import com.neteast.business.domain.rendering.SysDynamicRendering;
import com.neteast.business.service.IAgencyMessageService;
import com.neteast.business.service.ISysDynamicRenderingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月19 11:03
 */

@RestController
@RequestMapping("/agencyMessage")
public class AgencyMessageController extends BaseController{

    @Resource
    IAgencyMessageService agencyMessageService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/listByPage")
    public AjaxResult getAgencyMessageListByPage(AgencyMessage agencyMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<AgencyMessage> list = agencyMessageService.getAgencyMessageList(agencyMessage);
        TableDataInfo info = getDataTable(list);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("custom","agency","list");
        initPageParams(rendering,info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/toModify/{id}")
    public AjaxResult toModify(@PathVariable("id")Integer id){

        AgencyMessage message = agencyMessageService.getById(id);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("custom","agency","toModify");
        rendering.put("data",message);
        return success(rendering);
    }

    @GetMapping("/toAdd")
    public AjaxResult toAdd(){

        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("custom","agency","toAdd");
        return success(rendering);
    }

    @GetMapping("/list")
    public AjaxResult getAgencyMessageList(AgencyMessage agencyMessage){

        List<AgencyMessage> list = agencyMessageService.getAgencyMessageList(agencyMessage);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addAgencyMessage(@RequestBody AgencyMessage agencyMessage){

        agencyMessageService.save(agencyMessage);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateAgencyMessage(@RequestBody AgencyMessage agencyMessage){

        agencyMessageService.updateById(agencyMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delAgencyMessage(@PathVariable("id")Integer id){

        agencyMessageService.removeById(id);
        return success();
    }

}
