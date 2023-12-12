package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.mapper.ProjectScoreItemMapper;
import com.neteast.business.service.IProjectScoreItemService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年12月12 14:15
 */

@Service
public class ProjectScoreItemServiceImpl extends ServiceImpl<ProjectScoreItemMapper, ProjectScoreItem> implements IProjectScoreItemService {
}
