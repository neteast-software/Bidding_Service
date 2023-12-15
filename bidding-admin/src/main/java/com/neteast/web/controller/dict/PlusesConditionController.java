package com.neteast.web.controller.dict;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.dict.PlusesCondition;
import com.neteast.business.service.IPlusesConditionService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 附加项内容 (环保加分等)
 * @author lzp
 * @date 2023年12月15 11:48
 */

@RestController
@RequestMapping("/plusesCondition")
public class PlusesConditionController extends BaseController {

    @Resource
    IPlusesConditionService plusesConditionService;

    @GetMapping("/listByPage")
    public AjaxResult getPlusesConditionListByPage(PlusesCondition plusesCondition){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<PlusesCondition> list = plusesConditionService.getPlusesConditionList(plusesCondition);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info, pageDomain.getPageSize(), pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/list")
    public AjaxResult getPlusesConditionList(PlusesCondition plusesCondition){

        List<PlusesCondition> list = plusesConditionService.getPlusesConditionList(plusesCondition);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addPlusesCondition(@RequestBody PlusesCondition plusesCondition){

        plusesConditionService.save(plusesCondition);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updatePlusesCondition(@RequestBody PlusesCondition plusesCondition){

        plusesConditionService.updateById(plusesCondition);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delPlusesCondition(@PathVariable("id") Integer id){

        plusesConditionService.removeById(id);
        return success();
    }

}
