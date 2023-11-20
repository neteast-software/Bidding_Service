package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectExpert;
import com.neteast.business.domain.project.vo.ProjectExpertVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:35
 */

@Mapper
public interface ProjectExpertMapper extends BaseMapper<ProjectExpert> {

    List<ProjectExpertVO> getList(ProjectExpertVO projectExpertVO);
}
