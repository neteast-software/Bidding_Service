package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.ExpertMessage;
import com.neteast.business.service.IContractMessageService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 17:51
 */

@RestController
@RequestMapping("/contractMessage")
public class ContractMessageController extends BaseController {

    @Resource
    IContractMessageService contractMessageService;

    @PostMapping("/list")
    public AjaxResult getContractMessageData(ContractMessage contractMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ContractMessage> list = contractMessageService.getContractMessageList(contractMessage);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addContractMessageData(@RequestBody ContractMessage contractMessage){
        contractMessageService.save(contractMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delContractMessageData(@PathVariable String id){
        contractMessageService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateContractMessageData(@RequestBody ContractMessage contractMessage){
        contractMessageService.updateById(contractMessage);
        return success();
    }
}
