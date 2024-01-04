package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.ExpertMessage;
import com.neteast.business.service.IExpertMessageService;
import com.neteast.business.service.ISysDynamicRenderingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专家信息管理
 * @author lzp
 * @date 2023年11月17 16:09
 */

@RestController
@RequestMapping("/expertMessage")
public class ExpertMessageController extends BaseController {

    @Resource
    IExpertMessageService expertMessageService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/listByPage")
    public AjaxResult getExpertMessageListByPage(ExpertMessage expertMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ExpertMessage> list = expertMessageService.getExpertMessageList(expertMessage);
        TableDataInfo info = getDataTable(list);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("custom","expert","list");
        initPageParams(rendering,info, pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/toModify/{id}")
    public AjaxResult toModify(@PathVariable("id")Integer id){

        ExpertMessage message = expertMessageService.getById(id);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("custom","expert","toModify");
        rendering.put("data",message);
        return success(rendering);
    }

    @GetMapping("/toAdd")
    public AjaxResult toAdd(){
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("custom","expert","toAdd");
        return success(rendering);
    }

    @GetMapping("/list")
    public AjaxResult getExpertMessageList(ExpertMessage expertMessage){

        List<ExpertMessage> list =  expertMessageService.getExpertMessageList(expertMessage);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addExpertMessageData(@RequestBody ExpertMessage expertMessage){
        expertMessageService.save(expertMessage);
        return success();
    }

    @DeleteMapping("/del/{id}")
    public AjaxResult delExpertMessageData(@PathVariable("id") String id){
        expertMessageService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateExpertMessageData(@RequestBody ExpertMessage expertMessage){
        expertMessageService.updateById(expertMessage);
        return success();
    }

}
