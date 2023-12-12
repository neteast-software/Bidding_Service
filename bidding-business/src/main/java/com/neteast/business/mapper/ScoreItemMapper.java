package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ScoreItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评分项的子项
 * @author lzp
 * @date 2023年12月12 14:13
 */

@Mapper
public interface ScoreItemMapper extends BaseMapper<ScoreItem> {

    List<ScoreItem> getList(ScoreItem scoreItem);
}
