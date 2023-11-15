package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.ProjectInformation;
import com.neteast.business.mapper.ProjectInformationMapper;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IProjectInformationService;

/**
 * @author lzp
 * @date 2023年11月15 11:56
 */

@Service
public class ProjectInformationServiceImpl extends ServiceImpl<ProjectInformationMapper, ProjectInformation> implements IProjectInformationService {
}
