package com.neteast.web.controller.business;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.FailBiddingMsg;
import com.neteast.business.service.IFailBiddingMsgService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 流标记录信息
 * @author lzp
 * @date 2023年11月15 11:49
 */

@RestController
@RequestMapping("/failBiddingMsg")
public class FailBiddingMsgController extends BaseController {

    @Resource
    IFailBiddingMsgService projectBiddingMsgService;

    @GetMapping("/history")
    public AjaxResult getProjectBiddingMsgHistory(FailBiddingMsg failBiddingMsg){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<FailBiddingMsg> list = projectBiddingMsgService.getProjectBiddingMsgList(failBiddingMsg);
        TableDataInfo info = getDataTable(list);
        JSONObject data = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(data);
    }

}
