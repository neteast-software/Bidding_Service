package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectSupplierMessage;
import com.neteast.business.mapper.SupplierMessageMapper;
import com.neteast.business.service.ISupplierMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 14:01
 */

@Service
public class SupplierMessageServiceImpl extends ServiceImpl<SupplierMessageMapper, ProjectSupplierMessage> implements ISupplierMessageService {

    @Resource
    SupplierMessageMapper supplierMessageMapper;

    @Override
    public List<ProjectSupplierMessage> getSupplierMessageList(ProjectSupplierMessage projectSupplierMessage) {
        return supplierMessageMapper.getList(projectSupplierMessage);
    }
}
