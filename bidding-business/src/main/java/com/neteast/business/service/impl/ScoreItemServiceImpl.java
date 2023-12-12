package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.mapper.ScoreItemMapper;
import com.neteast.business.service.IScoreItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月12 14:16
 */

@Service
public class ScoreItemServiceImpl extends ServiceImpl<ScoreItemMapper, ScoreItem> implements IScoreItemService {

    @Override
    public List<ScoreItem> getListByExtId(Integer id) {
        return this.lambdaQuery().eq(ScoreItem::getExtId,id).list();
    }
}
