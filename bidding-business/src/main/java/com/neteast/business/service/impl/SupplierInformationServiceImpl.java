package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.SupplierInformation;
import com.neteast.business.mapper.SupplierInformationMapper;
import com.neteast.business.service.IProjectInformationService;
import com.neteast.business.service.ISupplierInformationService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年12月11 18:06
 */

@Service
public class SupplierInformationServiceImpl extends ServiceImpl<SupplierInformationMapper, SupplierInformation> implements ISupplierInformationService {
}
