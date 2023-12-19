package com.neteast.web.controller.template;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.template.ScoreTemplate;
import com.neteast.business.service.IScoreTemplateService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评分项子项模板
 * @author lzp
 * @date 2023年12月15 18:43
 */

@RestController
@RequestMapping("/scoreTemplate")
public class ScoreTemplateController extends BaseController {

    @Resource
    IScoreTemplateService scoreTemplateService;

    @GetMapping("/list")
    public AjaxResult getScoreTemplateList(ScoreTemplate scoreTemplate){

        List<ScoreTemplate> list = scoreTemplateService.getScoreTemplateList(scoreTemplate);
        return success(list);
    }

    @GetMapping("/listByPage")
    public AjaxResult getScoreTemplateListByPage(ScoreTemplate scoreTemplate){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ScoreTemplate> list = scoreTemplateService.getScoreTemplateList(scoreTemplate);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info, pageDomain.getPageSize(), pageDomain.getPageNum());
        return success(body);
    }



}
