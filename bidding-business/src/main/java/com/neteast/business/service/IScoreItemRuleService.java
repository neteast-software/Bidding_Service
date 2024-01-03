package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ScoreItemRule;

/**
 * @author lzp
 * @date 2024年01月02 10:44
 */
public interface IScoreItemRuleService extends IService<ScoreItemRule> {

    ScoreItemRule getScoreItemRole(ScoreItemRule rule);
}
