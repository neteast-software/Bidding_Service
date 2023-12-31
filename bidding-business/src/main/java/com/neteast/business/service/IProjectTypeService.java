package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectType;
import com.neteast.business.domain.project.vo.ProjectTypeVO;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月18 16:22
 */
public interface IProjectTypeService extends IService<ProjectType> {

    List<ProjectTypeVO> getProjectTypeList(ProjectTypeVO projectTypeVO);
}
