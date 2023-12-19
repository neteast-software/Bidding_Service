package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.template.ScoreTemplate;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月15 18:38
 */
public interface IScoreTemplateService extends IService<ScoreTemplate> {

    List<ScoreTemplate> getScoreTemplateList(ScoreTemplate scoreTemplate);

    boolean removeByExtId(Integer id);
}
