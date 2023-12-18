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

    @GetMapping("/getOne/{id}")
    public AjaxResult getWorkPlaceNameOne(@PathVariable("id")Integer id){

        WorkPlaceName  workPlaceName = workPlaceNameService.getById(id);
        if (workPlaceName!=null){
            WorkPlaceNameVO temp = WorkPlaceNameVO.convert(workPlaceName);
            BankMessage bankMessage = BankMessage.builder().extId(temp.getId()).build();
            ContractMessage contractMessage = ContractMessage.builder().extId(temp.getId()).build();
            temp.setBankMessages(bankMessageService.getBankMessageByExId(bankMessage));
            temp.setContractMessages(contractMessageService.getContractMessageByExId(contractMessage));
            return success(temp);
        }
        return error("该单位信息不存在");
    }

    @GetMapping("/listByPage")
    public AjaxResult getWorkPlaceNameListByPage(WorkPlaceName workPlaceName){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<WorkPlaceName> list = workPlaceNameService.getWorkPlaceNameList(workPlaceName);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/list")
    public AjaxResult getWorkPlaceNameList(WorkPlaceName workPlaceName){

        List<WorkPlaceName> list = workPlaceNameService.getWorkPlaceNameList(workPlaceName);
        return success(list);
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
