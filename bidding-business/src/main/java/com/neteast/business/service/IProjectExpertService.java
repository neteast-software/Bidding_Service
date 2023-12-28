package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectExpert;
import com.neteast.business.domain.project.vo.ExpertSubmitVO;
import com.neteast.business.domain.project.vo.ProjectExpertVO;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:41
 */
public interface IProjectExpertService extends IService<ProjectExpert> {

    List<ProjectExpertVO> getProjectExpertList(ProjectExpertVO projectExpertVO);

    boolean updateExpertStepStatus(ExpertSubmitVO expertSubmitVO);
}
