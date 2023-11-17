package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.SupplierMessage;
import com.neteast.business.mapper.SupplierMessageMapper;
import com.neteast.business.service.ISupplierMessageService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月17 14:01
 */

@Service
public class SupplierMessageServiceImpl extends ServiceImpl<SupplierMessageMapper, SupplierMessage> implements ISupplierMessageService {
}
