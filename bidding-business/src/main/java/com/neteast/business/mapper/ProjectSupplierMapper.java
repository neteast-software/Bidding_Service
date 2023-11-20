package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectSupplier;
import com.neteast.business.domain.project.vo.ProjectSupplierVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 15:36
 */

@Mapper
public interface ProjectSupplierMapper extends BaseMapper<ProjectSupplier> {

    List<ProjectSupplierVO> getList(ProjectSupplierVO projectSupplierVO);
}
