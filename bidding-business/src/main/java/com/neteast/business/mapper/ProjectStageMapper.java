package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectStage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 15:44
 */

@Mapper
public interface ProjectStageMapper extends BaseMapper<ProjectStage> {

    List<ProjectStage> getList(ProjectStage projectStage);
}
