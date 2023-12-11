package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.SupplierInformation;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月11 18:05
 */
public interface ISupplierInformationService extends IService<SupplierInformation> {

    List<SupplierInformation> getList(Integer projectId,Integer packageId);
}
