package com.neteast.web.controller.project;

import com.neteast.business.domain.project.ScoreItemRule;
import com.neteast.business.service.IScoreItemRuleService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
        return success();
    }
}
