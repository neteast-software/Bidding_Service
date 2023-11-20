package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.vo.BankMessageVO;
import com.neteast.business.service.IBankMessageService;
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
 * @date 2023年11月17 17:52
 */

@RestController
@RequestMapping("/bankMessage")
public class BankMessageController extends BaseController {

    @Resource
    IBankMessageService bankMessageService;

    @GetMapping("/list")
    public AjaxResult getBankMessageList(BankMessageVO bankMessageVO){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<BankMessageVO> list = bankMessageService.getBankMessageList(bankMessageVO);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addBankMessageData(@RequestBody BankMessage bankMessage){
        bankMessageService.save(bankMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delBankMessageData(@PathVariable String id){
        bankMessageService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateBankMessageData(@RequestBody BankMessage bankMessage){
        bankMessageService.updateById(bankMessage);
        return success();
    }
}
