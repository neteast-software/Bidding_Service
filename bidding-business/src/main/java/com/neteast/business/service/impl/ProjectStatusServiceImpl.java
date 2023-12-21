package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectStatus;
import com.neteast.business.mapper.ProjectStatusMapper;
import com.neteast.business.service.IProjectStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 17:40
 */

@Service
public class ProjectStatusServiceImpl extends ServiceImpl<ProjectStatusMapper, ProjectStatus> implements IProjectStatusService {

    @Override
    public List<ProjectStatus> getProjectStatusListById(Integer projectId) {
        List<ProjectStatus> list = this.lambdaQuery().eq(ProjectStatus::getProjectId,projectId).orderByDesc(ProjectStatus::getStepNum).list();
        return list;
    }
}
