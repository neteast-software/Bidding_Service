package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ScoreItemRule;
import com.neteast.business.domain.project.vo.ScoreItemRuleVO;
import com.neteast.business.service.IScoreItemRuleService;
import com.neteast.business.service.ISysDynamicRenderingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评分项规则
 * @author lzp
 * @date 2024年01月02 11:56
 */

@RestController
@RequestMapping("/scoreItemRule")
public class ScoreItemRuleController extends BaseController {

    @Resource
    IScoreItemRuleService scoreItemRuleService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/listByPage")
    public AjaxResult getScoreItemRuleListByPage(ScoreItemRule scoreItemRule){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ScoreItemRuleVO> list = scoreItemRuleService.getScoreItemListByDict(scoreItemRule);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","scoreItemRule","list");
        TableDataInfo info = getDataTable(list);
        initPageParams(rendering,info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/toModify/{id}")
    public AjaxResult toModify(@PathVariable("id")Integer id){

        ScoreItemRule rule = scoreItemRuleService.getById(id);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","scoreItemRule","toModify");
        rendering.put("data",rule);
        return success(rendering);
    }

    @GetMapping("/toAdd")
    public AjaxResult toAdd(){

        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","scoreItemRule","toAdd");
        return success(rendering);
    }

    @PostMapping("/add")
    public AjaxResult addScoreItemRule(@RequestBody ScoreItemRule rule){

        scoreItemRuleService.save(rule);
        return addSuccess();
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
