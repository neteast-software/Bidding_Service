package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.PartyaMessage;
import com.neteast.business.domain.custom.vo.AgentMessageVO;
import com.neteast.business.domain.custom.vo.PartyaMessageVO;
import com.neteast.business.service.IBankMessageService;
import com.neteast.business.service.IContractMessageService;
import com.neteast.business.service.IPartyaMessageService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.common.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 甲方公司信息
 * @author lzp
 * @date 2023年11月15 11:48
 */

@RestController
@RequestMapping("/partyaMessage")
public class PartyaMessageController extends BaseController {

    @Resource
    IPartyaMessageService partyaMessageService;

    @Resource
    IContractMessageService contractMessageService;

    @Resource
    IBankMessageService bankMessageService;

    @GetMapping("/list")
    public AjaxResult getPartyAMessageList(PartyaMessage partyaMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<PartyaMessage> list = partyaMessageService.getPartyAMessageList(partyaMessage);
        List<PartyaMessageVO> voList = new ArrayList<>();
        list.forEach(one->{
            PartyaMessageVO temp = PartyaMessageVO.convert(one);
            BankMessage bankMessage = BankMessage.builder().extId(one.getId()).type(1).build();
            ContractMessage contractMessage = ContractMessage.builder().extId(one.getId()).type(1).build();
            temp.setBankMessages(bankMessageService.getBankMessageByType(bankMessage));
            temp.setContractMessages(contractMessageService.getContractMessageByType(contractMessage));
            voList.add(temp);
        });
        TableDataInfo info = getDataTable(voList);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addPartyAMessageData(@RequestBody PartyaMessage partyaMessage){
        partyaMessage.setCreateTime(new Date());
        partyaMessage.setCreateBy(SecurityUtils.getUsername());
        partyaMessageService.save(partyaMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delPartyAMessageData(@PathVariable Integer id){
        partyaMessageService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updatePartyAMessage(@RequestBody PartyaMessage partyaMessage){
        partyaMessage.setUpdateBy(SecurityUtils.getUsername());
        partyaMessage.setUpdateTime(new Date());
        partyaMessageService.updateById(partyaMessage);
        return success();
    }

}
