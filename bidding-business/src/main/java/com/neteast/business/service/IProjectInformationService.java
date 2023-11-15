package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.ProjectInformation;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:53
 */
public interface IProjectInformationService extends IService<ProjectInformation> {

    List<ProjectInformation> getProjectInformationList(ProjectInformation projectInformation);

    boolean updateProjectInformation(ProjectInformation projectInformation);
}
