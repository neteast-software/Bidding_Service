package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.mapper.PackageInformationMapper;
import com.neteast.business.service.IPackageInformationService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月17 14:00
 */

@Service
public class PackageInformationServiceImpl extends ServiceImpl<PackageInformationMapper, PackageInformation> implements IPackageInformationService {
}
