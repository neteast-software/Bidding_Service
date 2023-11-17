package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.SupplierMessage;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:55
 */
public interface ISupplierMessageService extends IService<SupplierMessage> {

    List<SupplierMessage> getSupplierMessageList(SupplierMessage supplierMessage);
}
