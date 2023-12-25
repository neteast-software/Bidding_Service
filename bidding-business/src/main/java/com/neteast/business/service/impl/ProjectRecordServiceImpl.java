package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.editor.ProjectBidding;
import com.neteast.business.domain.editor.ProjectRecord;
import com.neteast.business.mapper.ProjectRecordMapper;
import com.neteast.business.service.IProjectRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月25 10:47
 */

@Service
public class ProjectRecordServiceImpl extends ServiceImpl<ProjectRecordMapper, ProjectRecord> implements IProjectRecordService {

    @Resource
    ProjectRecordMapper projectRecordMapper;

    @Override
    public List<ProjectRecord> getProjectRecordList(ProjectRecord projectRecord) {
        return projectRecordMapper.getList(projectRecord);
    }

    @Override
    public boolean updateFileMsg(Integer fileId, String fileName) {
        return this.lambdaUpdate().set(ProjectRecord::getFileName,fileName).eq(ProjectRecord::getFileId,fileId).update();
    }

    @Override
    public boolean updateUserMsg(Integer userId, String userName) {
        return this.lambdaUpdate().set(ProjectRecord::getOperaUser,userName).eq(ProjectRecord::getUserId,userId).update();
    }
}
