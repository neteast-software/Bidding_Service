package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectSupplierMessage;
import com.neteast.business.mapper.ProjectSupplierMessageMapper;
import com.neteast.business.service.IProjectSupplierMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 14:01
 */

@Service
public class ProjectSupplierMessageServiceImpl extends ServiceImpl<ProjectSupplierMessageMapper, ProjectSupplierMessage> implements IProjectSupplierMessageService {

    @Resource
    ProjectSupplierMessageMapper projectSupplierMessageMapper;

    @Override
    public List<ProjectSupplierMessage> getSupplierMessageList(ProjectSupplierMessage projectSupplierMessage) {
        return projectSupplierMessageMapper.getList(projectSupplierMessage);
    }
}
