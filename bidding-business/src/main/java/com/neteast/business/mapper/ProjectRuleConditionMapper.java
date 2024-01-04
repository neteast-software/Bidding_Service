package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectRuleCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2024年01月04 11:13
 */

@Mapper
public interface ProjectRuleConditionMapper extends BaseMapper<ProjectRuleCondition> {

    List<ProjectRuleCondition> getList(ProjectRuleCondition ruleCondition);
}
