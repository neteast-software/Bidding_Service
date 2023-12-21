package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectStatus;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 17:39
 */
public interface IProjectStatusService extends IService<ProjectStatus> {

    List<ProjectStatus> getProjectStatusListById(Integer projectId);
}
