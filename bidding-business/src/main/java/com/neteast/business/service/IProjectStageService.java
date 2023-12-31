package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectStage;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 15:44
 */
public interface IProjectStageService extends IService<ProjectStage> {

    List<ProjectStage> getProjectStageList(ProjectStage projectStage);

    List<ProjectStage> getProjectStageListById(Integer projectTypeId);

    boolean addProjectStage(ProjectStage projectStage);

    boolean delProjectStage(Integer id);

    boolean updateProjectStage(ProjectStage projectStage);

    boolean updateTemplateMessage(Integer templateId,String templateName);
}
