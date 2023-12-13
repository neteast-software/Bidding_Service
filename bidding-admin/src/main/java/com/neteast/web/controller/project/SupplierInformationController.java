package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.SupplierInformation;
import com.neteast.business.service.ISupplierInformationService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目供应商信息
 * @author lzp
 * @date 2023年12月11 18:24
 */

@RestController
@RequestMapping("/supplierInformation")
public class SupplierInformationController extends BaseController {

    @Resource
    ISupplierInformationService supplierInformationService;

    @GetMapping("/getList")
    public AjaxResult getSupplierInformation(SupplierInformation supplierInformation){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<SupplierInformation> list = supplierInformationService.getList(supplierInformation);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addSupplierInformation(@RequestBody SupplierInformation supplierInformation){
        supplierInformationService.save(supplierInformation);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateSupplierInformation(@RequestBody SupplierInformation supplierInformation){
        supplierInformationService.updateById(supplierInformation);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delSupplierInformation(@PathVariable("id")Integer id){
        supplierInformationService.removeById(id);
        return success();
    }

}
