package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.mapper.ScoreItemMapper;
import com.neteast.business.service.IProjectScoreItemService;
import com.neteast.business.service.IScoreItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月12 14:16
 */

@Service
public class ScoreItemServiceImpl extends ServiceImpl<ScoreItemMapper, ScoreItem> implements IScoreItemService {

    @Resource
    IProjectScoreItemService projectScoreItemService;

    @Override
    public List<ScoreItem> getListByExtId(Integer id) {
        return this.lambdaQuery().eq(ScoreItem::getExtId,id).list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addScoreItem(ScoreItem scoreItem) {
        ProjectScoreItem projectScoreItem = projectScoreItemService.lambdaQuery().eq(ProjectScoreItem::getId,scoreItem.getExtId()).one();
        save(scoreItem);
        projectScoreItem.changeNum(1);
        projectScoreItem.changeValue(scoreItem.getValue());
        projectScoreItemService.updateById(projectScoreItem);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeScoreItem(Integer id) {

        ScoreItem item = getById(id);
        ProjectScoreItem projectScoreItem = projectScoreItemService.lambdaQuery().eq(ProjectScoreItem::getId,item.getExtId()).one();
        removeById(id);
        projectScoreItem.changeNum(-1);
        projectScoreItem.changeValue(-item.getValue());
        projectScoreItemService.updateById(projectScoreItem);
        return true;
    }

    @Override
    public boolean updateScoreItem(ScoreItem after) {

        ScoreItem before = getById(after.getId());
        if (after.getValue()!=null&&before.getValue().compareTo(after.getValue())!=0){
            ProjectScoreItem projectScoreItem = projectScoreItemService.lambdaQuery().eq(ProjectScoreItem::getId,after.getExtId()).one();
            Double change = after.getValue()-before.getValue();
            projectScoreItem.changeValue(change);
            projectScoreItemService.updateById(projectScoreItem);
        }
        updateById(after);
        return true;
    }

    @Override
    public boolean removeByProjectId(Integer projectId) {
        QueryWrapper<ScoreItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id",projectId);
        return this.remove(queryWrapper);
    }
}
