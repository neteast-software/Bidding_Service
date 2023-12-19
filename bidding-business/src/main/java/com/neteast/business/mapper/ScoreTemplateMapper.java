package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.template.ScoreTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月15 18:37
 */

@Mapper
public interface ScoreTemplateMapper extends BaseMapper<ScoreTemplate> {

    List<ScoreTemplate> getList(ScoreTemplate scoreTemplate);
}
