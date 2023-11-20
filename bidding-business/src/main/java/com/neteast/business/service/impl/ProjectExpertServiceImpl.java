package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectExpert;
import com.neteast.business.domain.project.vo.ProjectExpertVO;
import com.neteast.business.mapper.ProjectExpertMapper;
import com.neteast.business.service.IProjectExpertService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:45
 */
public class ProjectExpertServiceImpl extends ServiceImpl<ProjectExpertMapper, ProjectExpert> implements IProjectExpertService {

    @Resource
    ProjectExpertMapper projectExpertMapper;

    @Override
    public List<ProjectExpertVO> getProjectExpertList(ProjectExpertVO projectExpertVO) {
        return projectExpertMapper.getList(projectExpertVO);
    }
}
