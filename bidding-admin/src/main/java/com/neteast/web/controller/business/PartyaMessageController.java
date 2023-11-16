package com.neteast.web.controller.business;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.PartyaMessage;
import com.neteast.business.service.IPartyaMessageService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.common.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @GetMapping("/list")
    public AjaxResult getPartyAMessageList(PartyaMessage partyaMessage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<PartyaMessage> list = partyaMessageService.getPartyAMessageList(partyaMessage);
        TableDataInfo info = getDataTable(list);
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
