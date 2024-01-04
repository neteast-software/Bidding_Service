package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.PurchaserMessage;
import com.neteast.business.service.IPurchaserMessageService;
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
 * @date 2023年12月19 11:02
 */

@RestController
@RequestMapping("/purchaserMessage")
public class PurchaserMessageController extends BaseController {

    @Resource
    IPurchaserMessageService purchaserMessageService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/listByPage")
    public AjaxResult getPurchaserMessageListByPage(PurchaserMessage purchaserMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<PurchaserMessage> list = purchaserMessageService.getPurchaserMessageList(purchaserMessage);
        TableDataInfo info = getDataTable(list);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("custom","purchaser","list");
        initPageParams(rendering,info, pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/toModify/{id}")
    public AjaxResult toModify(@PathVariable("id")Integer id){
        PurchaserMessage message = purchaserMessageService.getById(id);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("custom","purchaser","toModify");
        rendering.put("data",message);
        return success(rendering);
    }

    @GetMapping("/toAdd")
    public AjaxResult toAdd(){
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("custom","purchaser","toAdd");
        return success(rendering);
    }

    @GetMapping("/list")
    public AjaxResult getPurchaserMessageList(PurchaserMessage purchaserMessage){
        System.out.println(purchaserMessage);
        List<PurchaserMessage> list = purchaserMessageService.getPurchaserMessageList(purchaserMessage);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addPurchaserMessage(@RequestBody PurchaserMessage purchaserMessage){

        purchaserMessageService.save(purchaserMessage);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updatePurchaserMessage(@RequestBody PurchaserMessage purchaserMessage){

        purchaserMessageService.updateById(purchaserMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delPurchaserMessage(@PathVariable("id") Integer id){

        purchaserMessageService.removeById(id);
        return success();
    }

}
