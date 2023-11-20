package com.neteast.web.controller.custom;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.WorkPlaceName;
import com.neteast.business.domain.custom.vo.WorkPlaceNameVO;
import com.neteast.business.service.IBankMessageService;
import com.neteast.business.service.IContractMessageService;
import com.neteast.business.service.IWorkPlaceNameService;
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
 * @date 2023年11月17 18:30
 */

@RestController
@RequestMapping("/workPlaceName")
public class WorkPlaceNameController extends BaseController {

    @Resource
    IWorkPlaceNameService workPlaceNameService;

    @Resource
    IContractMessageService contractMessageService;

    @Resource
    IBankMessageService bankMessageService;

    @GetMapping("/list")
    public AjaxResult getWorkPlaceNameList(WorkPlaceName workPlaceName){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<WorkPlaceName> list = workPlaceNameService.getWorkPlaceNameList(workPlaceName);
        List<WorkPlaceNameVO> voList = new ArrayList<>();
        list.forEach(one->{
            WorkPlaceNameVO temp = WorkPlaceNameVO.convert(one);
            BankMessage bankMessage = BankMessage.builder().extId(one.getId()).build();
            ContractMessage contractMessage = ContractMessage.builder().extId(one.getId()).build();
            temp.setBankMessages(bankMessageService.getBankMessageByExId(bankMessage));
            temp.setContractMessages(contractMessageService.getContractMessageByExId(contractMessage));
            voList.add(temp);
        });
        TableDataInfo info = getDataTable(voList);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addAgencyBiddingData(@RequestBody WorkPlaceName agentMessage){
        workPlaceNameService.save(agentMessage);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delAgencyBiddingData(@PathVariable Integer id){
        workPlaceNameService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateAgencyBiddingData(@RequestBody WorkPlaceName agentMessage){
        workPlaceNameService.updateById(agentMessage);
        return success();
    }
}
