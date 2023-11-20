package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:20
 */

@Mapper
public interface ProjectInformationMapper extends BaseMapper<ProjectInformation> {

    List<ProjectInformation> getList(ProjectInformation projectInformation);
}
