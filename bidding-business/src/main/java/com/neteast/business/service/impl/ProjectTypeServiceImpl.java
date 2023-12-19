package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectType;
import com.neteast.business.domain.project.vo.ProjectTypeVO;
import com.neteast.business.mapper.ProjectTypeMapper;
import com.neteast.business.service.IProjectTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月18 16:24
 */

@Service
public class ProjectTypeServiceImpl extends ServiceImpl<ProjectTypeMapper, ProjectType> implements IProjectTypeService {

    @Resource
    ProjectTypeMapper projectTypeMapper;

    @Override
    public List<ProjectTypeVO> getProjectTypeList(ProjectTypeVO projectTypeVO) {
        return projectTypeMapper.getList(projectTypeVO);
    }
}
