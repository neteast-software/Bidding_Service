package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.SupplierMessage;
import com.neteast.business.domain.custom.vo.PartyaMessageVO;
import com.neteast.business.domain.custom.vo.SupplierMessageVO;
import com.neteast.business.service.IBankMessageService;
import com.neteast.business.service.IContractMessageService;
import com.neteast.business.service.ISupplierMessageService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 17:12
 */

@RestController
@RequestMapping("/supplierMessage")
public class SupplierMessageController extends BaseController {

    @Resource
    ISupplierMessageService supplierMessageService;

    @Resource
    IContractMessageService contractMessageService;

    @Resource
    IBankMessageService bankMessageService;

    @GetMapping("/list")
    public AjaxResult getSupplierMessageList(SupplierMessage supplierMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<SupplierMessage> list = supplierMessageService.getSupplierMessageList(supplierMessage);
        List<SupplierMessageVO> voList = new ArrayList<>();
        list.forEach(one->{
            SupplierMessageVO temp = SupplierMessageVO.convert(one);
            BankMessage bankMessage = BankMessage.builder().extId(one.getId()).type(3).build();
            ContractMessage contractMessage = ContractMessage.builder().extId(one.getId()).type(3).build();
            temp.setBankMessages(bankMessageService.getBankMessageByType(bankMessage));
            temp.setContractMessages(contractMessageService.getContractMessageByType(contractMessage));
            voList.add(temp);
        });
        TableDataInfo info = getDataTable(voList);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addSupplierMessageData(@RequestBody SupplierMessage supplierMessage){
        supplierMessageService.save(supplierMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delSupplierMessageData(@PathVariable String id){
        supplierMessageService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateSupplierMessageData(@RequestBody SupplierMessage supplierMessage){
        supplierMessageService.updateById(supplierMessage);
        return success();
    }

}
