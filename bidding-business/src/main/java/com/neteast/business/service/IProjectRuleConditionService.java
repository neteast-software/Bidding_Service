package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectRuleCondition;

import java.util.List;

/**
 * @author lzp
 * @date 2024年01月04 11:14
 */
public interface IProjectRuleConditionService extends IService<ProjectRuleCondition> {

    List<ProjectRuleCondition> getProjectRuleConditionList(ProjectRuleCondition projectRuleCondition);
}
