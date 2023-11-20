package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectTypeInformation;
import com.neteast.business.domain.project.vo.ProjectTypeInformationVO;
import com.neteast.business.mapper.ProjectTypeInformationMapper;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IProjectTypeInformationService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:56
 */

@Service
public class ProjectTypeInformationServiceImpl extends ServiceImpl<ProjectTypeInformationMapper, ProjectTypeInformation> implements IProjectTypeInformationService {

    @Resource
    ProjectTypeInformationMapper projectTypeInformationMapper;

    @Override
    public List<ProjectTypeInformationVO> getProjectTypeList(ProjectTypeInformationVO projectTypeInformationVO) {
        return projectTypeInformationMapper.getList(projectTypeInformationVO);
    }
}
