package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ProjectSupplierMessage;
import com.neteast.business.service.IProjectSupplierMessageService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 甲方供应商信息
 * @author lzp
 * @date 2023年11月17 16:33
 */

@RestController
@RequestMapping("/projectSupplierMessage")
public class ProjectSupplierMessageController extends BaseController {

    @Resource
    IProjectSupplierMessageService supplierMessageService;

    @GetMapping("/list")
    public AjaxResult getSupplierMessageList(ProjectSupplierMessage projectSupplierMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectSupplierMessage> list = supplierMessageService.getSupplierMessageList(projectSupplierMessage);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addSupplierMessageData(@RequestBody ProjectSupplierMessage projectSupplierMessage){
        supplierMessageService.save(projectSupplierMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delSupplierMessageData(@PathVariable Integer id){

        supplierMessageService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateSupplierMessageData(@RequestBody ProjectSupplierMessage projectSupplierMessage){

        supplierMessageService.updateById(projectSupplierMessage);
        return success();
    }

}
