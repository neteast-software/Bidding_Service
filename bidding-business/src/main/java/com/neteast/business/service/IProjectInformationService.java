package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.vo.ProjectInformationVO;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:53
 */
public interface IProjectInformationService extends IService<ProjectInformation> {

    List<ProjectInformation> getProjectInformationList(ProjectInformation projectInformation);

    boolean updateProjectInformation(ProjectInformationVO projectInformationVO);

    boolean addProjectInformation(ProjectInformationVO projectInformationVO);

    boolean delProjectInformation(Integer id);
}
