package com.neteast.business.service.impl;

import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.domain.project.vo.ProjectTypeInformationVO;
import com.neteast.business.mapper.ProjectInformationMapper;
import com.neteast.business.service.ProjectTypeInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月21 11:24
 */

@Service
public class ProjectTypeInformationServiceImpl implements ProjectTypeInformationService {

    @Resource
    ProjectInformationMapper projectInformationMapper;

    @Override
    public List<ProjectTypeInformationVO> getProjectTypeInfoList(ProjectTypeInformationVO projectTypeInformationVO) {
        return projectInformationMapper.getListByType(projectTypeInformationVO);
    }
}
