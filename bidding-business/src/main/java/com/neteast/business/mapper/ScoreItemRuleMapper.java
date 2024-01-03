package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ScoreItemRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2024年01月02 10:43
 */

@Mapper
public interface ScoreItemRuleMapper extends BaseMapper<ScoreItemRule> {

    List<ScoreItemRule> getList(ScoreItemRule scoreItemRule);
}
