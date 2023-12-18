package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectCondition;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月12 11:10
 */
public interface IProjectPlusConditionService extends IService<ProjectCondition> {

    List<ProjectCondition> getProjectPlusConditionList(ProjectCondition projectCondition);
}
