package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月18 16:21
 */

@Mapper
public interface ProjectTypeMapper extends BaseMapper<ProjectType> {

    List<ProjectType> getList(ProjectType projectType);
}
