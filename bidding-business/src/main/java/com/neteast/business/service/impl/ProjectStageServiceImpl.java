package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectStage;
import com.neteast.business.mapper.ProjectStageMapper;
import com.neteast.business.service.IProjectStageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 15:45
 */

@Service
public class ProjectStageServiceImpl extends ServiceImpl<ProjectStageMapper, ProjectStage> implements IProjectStageService {

    @Resource
    ProjectStageMapper projectStageMapper;

    @Override
    public List<ProjectStage> getProjectStageList(ProjectStage projectStage) {
        return projectStageMapper.getList(projectStage);
    }

    @Override
    public List<ProjectStage> getProjectStageListById(Integer projectTypeId) {
        return this.lambdaQuery().eq(ProjectStage::getProjectTypeId,projectTypeId).orderByDesc(ProjectStage::getStepNum).list();
    }
}
