package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectSupplierMessage;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:55
 */
public interface IProjectSupplierMessageService extends IService<ProjectSupplierMessage> {

    List<ProjectSupplierMessage> getSupplierMessageList(ProjectSupplierMessage projectSupplierMessage);
}
