package com.neteast.web.controller.dict;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.dict.DictHistory;
import com.neteast.business.service.IDictHistoryService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 变量历史记录
 * @author lzp
 * @date 2023年11月22 10:59
 */

@RestController
@RequestMapping("/dictHistory")
public class DictHistoryController extends BaseController {

    @Resource
    IDictHistoryService dictHistoryService;

    @GetMapping("/list")
    public AjaxResult getDictHistoryList(DictHistory dictHistory){

        startPage();
        PageDomain pageDomain = new PageDomain();
        List<DictHistory> list = dictHistoryService.getDictHistoryList(dictHistory);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/del/{id}")
    public AjaxResult delDictHistoryData(@PathVariable Integer id){

        dictHistoryService.removeById(id);
        return success();
    }

}
