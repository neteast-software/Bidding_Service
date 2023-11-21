package com.neteast.business.service;

import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.domain.project.vo.ProjectTypeInformationVO;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月21 11:24
 */
public interface ProjectTypeInformationService {

    List<ProjectTypeInformationVO> getProjectTypeInfoList(ProjectTypeInformationVO projectTypeInformationVO);
}
