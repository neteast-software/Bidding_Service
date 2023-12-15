package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectCondition;
import com.neteast.business.mapper.ProjectPlusConditionMapper;
import com.neteast.business.service.IProjectPlusConditionService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年12月12 11:10
 */

@Service
public class ProjectPlusConditionServiceImpl extends ServiceImpl<ProjectPlusConditionMapper, ProjectCondition> implements IProjectPlusConditionService {
}
