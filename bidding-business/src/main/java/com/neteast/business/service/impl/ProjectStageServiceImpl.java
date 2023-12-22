package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectStage;
import com.neteast.business.domain.project.ProjectType;
import com.neteast.business.mapper.ProjectStageMapper;
import com.neteast.business.service.IProjectStageService;
import com.neteast.business.service.IProjectTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    IProjectTypeService projectTypeService;

    @Override
    public List<ProjectStage> getProjectStageList(ProjectStage projectStage) {
        return projectStageMapper.getList(projectStage);
    }

    @Override
    public List<ProjectStage> getProjectStageListById(Integer projectTypeId) {
        return this.lambdaQuery().eq(ProjectStage::getProjectTypeId,projectTypeId).orderByDesc(ProjectStage::getStepNum).list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProjectStage(ProjectStage projectStage) {

        ProjectType projectType = projectTypeService.getById(projectStage.getProjectTypeId());
        projectType.changeStepNum(1);
        projectTypeService.updateById(projectType);
        save(projectStage);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delProjectStage(Integer id) {

        ProjectStage stage = getById(id);
        removeById(id);
        ProjectType projectType = projectTypeService.getById(stage.getProjectTypeId());
        projectType.changeStepNum(-1);
        projectTypeService.updateById(projectType);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProjectStage(ProjectStage after) {

        ProjectStage before = getById(after.getId());
        if (before.getProjectTypeId().compareTo(after.getProjectTypeId())!=0){
            ProjectType beforeType = projectTypeService.getById(before.getProjectTypeId());
            ProjectType afterType = projectTypeService.getById(after.getProjectTypeId());
            beforeType.changeStepNum(-1);
            afterType.changeStepNum(1);
            projectTypeService.updateById(beforeType);
            projectTypeService.updateById(afterType);
        }
        updateById(after);
        return false;
    }
}
