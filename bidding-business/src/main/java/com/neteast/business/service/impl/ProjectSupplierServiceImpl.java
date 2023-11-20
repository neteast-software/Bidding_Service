package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ProjectSupplier;
import com.neteast.business.domain.project.vo.ProjectSupplierVO;
import com.neteast.business.mapper.ProjectSupplierMapper;
import com.neteast.business.service.IProjectSupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:46
 */

@Service
public class ProjectSupplierServiceImpl extends ServiceImpl<ProjectSupplierMapper, ProjectSupplier> implements IProjectSupplierService {

    @Resource
    ProjectSupplierMapper projectSupplierMapper;

    @Override
    public List<ProjectSupplierVO> getProjectSupplierList(ProjectSupplierVO projectSupplierVO) {
        return projectSupplierMapper.getList(projectSupplierVO);
    }
}
