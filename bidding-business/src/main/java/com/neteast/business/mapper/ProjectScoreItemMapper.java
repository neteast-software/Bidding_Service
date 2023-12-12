package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectScoreItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评分项设置
 * @author lzp
 * @date 2023年12月12 14:13
 */

@Mapper
public interface ProjectScoreItemMapper extends BaseMapper<ProjectScoreItem> {
}
