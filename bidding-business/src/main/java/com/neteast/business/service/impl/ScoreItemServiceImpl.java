package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.mapper.ScoreItemMapper;
import com.neteast.business.service.IProjectScoreItemService;
import com.neteast.business.service.IScoreItemService;
import org.springframework.stereotype.Service;

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
    public boolean addScoreItem(ScoreItem scoreItem) {
        ProjectScoreItem projectScoreItem = projectScoreItemService.lambdaQuery().eq(ProjectScoreItem::getId,scoreItem).one();
        save(scoreItem);
        int num = projectScoreItem.getNum()+1;
        projectScoreItem.setNum(num);
        projectScoreItemService.updateById(projectScoreItem);
        return true;
    }
}
