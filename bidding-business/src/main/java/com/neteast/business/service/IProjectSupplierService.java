package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.ProjectSupplier;
import com.neteast.business.domain.project.vo.ProjectSupplierVO;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:42
 */
public interface IProjectSupplierService extends IService<ProjectSupplier> {

    List<ProjectSupplierVO> getProjectSupplierList(ProjectSupplierVO projectSupplierVO);
}
