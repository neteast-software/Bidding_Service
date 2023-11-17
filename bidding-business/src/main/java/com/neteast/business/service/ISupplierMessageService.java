package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.custom.SupplierMessage;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 17:17
 */
public interface ISupplierMessageService extends IService<SupplierMessage> {

    List<SupplierMessage> getSupplierMessageList(SupplierMessage supplierMessage);
}
