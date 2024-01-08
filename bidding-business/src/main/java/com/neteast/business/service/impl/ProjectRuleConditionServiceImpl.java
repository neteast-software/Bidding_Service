package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectRuleCondition;
import com.neteast.business.mapper.ProjectRuleConditionMapper;
import com.neteast.business.service.IProjectRuleConditionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2024年01月04 11:14
 */

@Service
public class ProjectRuleConditionServiceImpl extends ServiceImpl<ProjectRuleConditionMapper, ProjectRuleCondition> implements IProjectRuleConditionService {

    @Resource
    ProjectRuleConditionMapper projectRuleConditionMapper;

    @Override
    public List<ProjectRuleCondition> getProjectRuleConditionList(ProjectRuleCondition projectRuleCondition) {
        return projectRuleConditionMapper.getList(projectRuleCondition);
    }
}
