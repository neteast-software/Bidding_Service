package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 项目状态
 * @author lzp
 * @date 2023年12月21 17:38
 */

@Mapper
public interface ProjectStatusMapper extends BaseMapper<ProjectStatus> {

}
