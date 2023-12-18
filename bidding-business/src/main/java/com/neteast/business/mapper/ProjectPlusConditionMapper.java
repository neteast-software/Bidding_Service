package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月12 11:09
 */

@Mapper
public interface ProjectPlusConditionMapper extends BaseMapper<ProjectCondition> {

    List<ProjectCondition> getList(ProjectCondition condition);
}
