package com.neteast.business.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ScoreMethod;
import com.neteast.business.mapper.ScoreMethodMapper;
import com.neteast.business.service.IScoreMethodService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月22 11:43
 */

@Service
public class ScoreMethodServiceImpl extends ServiceImpl<ScoreMethodMapper, ScoreMethod> implements IScoreMethodService {

    @Override
    public List<ScoreMethod> getScoreMethodList(ScoreMethod scoreMethod) {
        return this.lambdaQuery().
                eq(StrUtil.isNotBlank(scoreMethod.getScoreName()),ScoreMethod::getScoreName,scoreMethod.getScoreName()).list();
    }
}
