package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ScoreItemRule;
import com.neteast.business.service.IScoreItemRuleService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评分项规则
 * @author lzp
 * @date 2024年01月02 11:56
 */

@RestController("/scoreItemRule")
public class ScoreItemRuleController extends BaseController {

    @Resource
    IScoreItemRuleService scoreItemRuleService;

    @PostMapping("/add")
    public AjaxResult addScoreItemRule(@RequestBody ScoreItemRule rule){

        scoreItemRuleService.save(rule);
        return addSuccess();
    }

    @GetMapping("/listByPage")
    public AjaxResult getScoreItemRuleListByPage(ScoreItemRule scoreItemRule){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ScoreItemRule> list = scoreItemRuleService.getScoreItemList(scoreItemRule);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/update")
    public AjaxResult updateScoreItemRule(@RequestBody ScoreItemRule scoreItemRule){

        scoreItemRuleService.updateById(scoreItemRule);
        return updateSuccess();
    }

    @DeleteMapping("/{id}")
    public AjaxResult delScoreItemRule(@PathVariable("id")Integer id){

        scoreItemRuleService.removeById(id);
        return delSuccess();
    }

}
