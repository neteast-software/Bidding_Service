package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.AgencyBidding;
import com.neteast.business.domain.project.SupplierMessage;
import com.neteast.business.service.ISupplierMessageService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 甲方供应商信息
 * @author lzp
 * @date 2023年11月17 16:33
 */

@RestController
@RequestMapping("/supplierMessage")
public class SupplierMessageController extends BaseController {

    @Resource
    ISupplierMessageService supplierMessageService;

    @GetMapping("/list")
    public AjaxResult getSupplierMessageList(SupplierMessage supplierMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<SupplierMessage> list = supplierMessageService.getSupplierMessageList(supplierMessage);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }


}
