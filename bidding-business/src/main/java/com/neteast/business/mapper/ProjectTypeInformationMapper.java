package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectTypeInformation;
import com.neteast.business.domain.project.vo.ProjectTypeInformationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:27
 */

@Mapper
public interface ProjectTypeInformationMapper extends BaseMapper<ProjectTypeInformation> {

    List<ProjectTypeInformationVO> getList(ProjectTypeInformationVO projectTypeInformationVO);
}
