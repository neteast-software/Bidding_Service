package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.ProjectTypeInformation;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:53
 */
public interface IProjectTypeInformationService extends IService<ProjectTypeInformation> {

    /**
     * @Description 获取项目类型
     * @author lzp
     * @Date 2023/11/15
     */
    List<String> getProjectType(ProjectTypeInformation projectTypeInformation);
}
