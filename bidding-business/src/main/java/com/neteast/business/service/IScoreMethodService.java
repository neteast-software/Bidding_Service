package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ScoreMethod;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月22 11:42
 */
public interface IScoreMethodService extends IService<ScoreMethod> {

    List<ScoreMethod> getScoreMethodList(ScoreMethod scoreMethod);
}
