package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.mapper.ProjectScoreItemMapper;
import com.neteast.business.mapper.ScoreItemMapper;
import com.neteast.business.service.IProjectScoreItemService;
import com.neteast.business.service.IScoreItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月12 14:15
 */

@Service
public class ProjectScoreItemServiceImpl extends ServiceImpl<ProjectScoreItemMapper, ProjectScoreItem> implements IProjectScoreItemService {

    @Resource
    ProjectScoreItemMapper projectScoreItemMapper;

    @Resource
    IScoreItemService scoreItemService;

    @Override
    public List<ProjectScoreItem> getProjectScoreItemList(ProjectScoreItem projectScoreItem) {
        return projectScoreItemMapper.getList(projectScoreItem);
    }

    @Override
    public List<ProjectScoreItem> getProjectScoreItemList(Integer projectId, Integer packageId) {
        return this.lambdaQuery().eq(ProjectScoreItem::getProjectId,projectId).eq(ProjectScoreItem::getPackageId,packageId)
                .orderByDesc(ProjectScoreItem::getSort).list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeProjectScoreItem(Integer id) {
        QueryWrapper<ScoreItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ext_id",id);
        scoreItemService.remove(queryWrapper);
        this.removeById(id);
        return true;
    }

    @Override
    public boolean clearProjectScoreRecord(Integer projectId) {
        return this.lambdaUpdate().eq(ProjectScoreItem::getProjectId,projectId)
                .set(ProjectScoreItem::getNum,0)
                .set(ProjectScoreItem::getValue,0.0)
                .update();
    }
}
