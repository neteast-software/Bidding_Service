package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.template.ScoreTemplate;
import com.neteast.business.mapper.ScoreTemplateMapper;
import com.neteast.business.service.IScoreTemplateService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年12月15 18:40
 */

@Service
public class ScoreTemplateServiceImpl extends ServiceImpl<ScoreTemplateMapper, ScoreTemplate> implements IScoreTemplateService {
}
