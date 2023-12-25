package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectStatus;
import com.neteast.business.mapper.ProjectStatusMapper;
import com.neteast.business.service.IProjectStatusService;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public boolean removeByExtId(Integer projectId, Integer stageId) {

        ProjectStatus one = this.lambdaQuery().eq(ProjectStatus::getProjectId,projectId)
                .eq(ProjectStatus::getStageId,stageId).one();
        one.changeNum(-1);
        if (one.getNum()==0){
            this.removeById(one);
        }else {
            one.setStageTime(new Date());
            this.updateById(one);
        }
        return true;
    }

    @Override
    public boolean updateStepMsg(Integer stageId, Integer stepNum) {
        return this.lambdaUpdate().eq(ProjectStatus::getStageId,stageId)
                .eq(ProjectStatus::getStepNum,stepNum).update();
    }

    @Override
    public boolean saveByExtId(ProjectStatus status) {
        Integer projectId = status.getProjectId();
        Integer stageId = status.getStageId();
        List<ProjectStatus> list = this.lambdaQuery().eq(ProjectStatus::getProjectId,projectId)
                .eq(ProjectStatus::getStageId,stageId).list();
        if (list.size()==0){
            status.setNum(1);
            save(status);
        }else {
            ProjectStatus temp = list.get(0);
            temp.changeNum(1);
            updateById(temp);
        }
        return true;
    }

    @Override
    public boolean updateTime(Integer projectId, Integer stageId) {
        return this.lambdaUpdate().set(ProjectStatus::getStageTime,new Date())
                .eq(ProjectStatus::getProjectId,projectId).eq(ProjectStatus::getStageId,stageId)
                .update();
    }
}
