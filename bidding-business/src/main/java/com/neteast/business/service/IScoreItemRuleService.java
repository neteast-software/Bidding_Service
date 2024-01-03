package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ScoreItemRule;

import java.util.List;

/**
 * @author lzp
 * @date 2024年01月02 10:44
 */
public interface IScoreItemRuleService extends IService<ScoreItemRule> {

    ScoreItemRule getScoreItemRole(ScoreItemRule rule,Integer projectId,Integer packageId);

    List<ScoreItemRule> getScoreItemList(ScoreItemRule scoreItemRule);
}
