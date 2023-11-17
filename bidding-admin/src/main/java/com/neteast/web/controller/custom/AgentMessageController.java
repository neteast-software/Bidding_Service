package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.AgentMessage;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.vo.AgentMessageVO;
import com.neteast.business.service.IAgentMessageService;
import com.neteast.business.service.IBankMessageService;
import com.neteast.business.service.IContractMessageService;
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
 * 招标代理商信息
 * @author lzp
 * @date 2023年11月15 11:47
 */

@RestController
@RequestMapping("/agencyBidding")
public class AgentMessageController extends BaseController {

    @Resource
    IAgentMessageService agencyBiddingService;

    @Resource
    IContractMessageService contractMessageService;

    @Resource
    IBankMessageService bankMessageService;

    @GetMapping("/list")
    public AjaxResult getAgencyBiddingList(AgentMessage agentMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<AgentMessage> list = agencyBiddingService.getAgencyBiddingData(agentMessage);
        List<AgentMessageVO> voList = new ArrayList<>();
        list.forEach(one->{
            AgentMessageVO temp = AgentMessageVO.convert(one);
            BankMessage bankMessage = BankMessage.builder().extId(one.getId()).type(2).build();
            ContractMessage contractMessage = ContractMessage.builder().extId(one.getId()).type(2).build();
            temp.setBankMessages(bankMessageService.getBankMessageByType(bankMessage));
            temp.setContractMessages(contractMessageService.getContractMessageByType(contractMessage));
            voList.add(temp);
        });
        TableDataInfo info = getDataTable(voList);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addAgencyBiddingData(@RequestBody AgentMessage agentMessage){
        agencyBiddingService.save(agentMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delAgencyBiddingData(@PathVariable Integer id){
        agencyBiddingService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateAgencyBiddingData(@RequestBody AgentMessage agentMessage){
        agencyBiddingService.updateById(agentMessage);
        return success();
    }
}
