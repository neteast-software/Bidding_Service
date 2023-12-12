package com.neteast.business.service.impl;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean removeProjectScoreItem(Integer id) {
        scoreItemService.remove(scoreItemService.lambdaQuery().eq(ScoreItem::getExtId,id));
        this.removeById(id);
        return true;
    }
}
