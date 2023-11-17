package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.ProjectSupplierMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:50
 */

@Mapper
public interface ProjectSupplierMessageMapper extends BaseMapper<ProjectSupplierMessage> {

    List<ProjectSupplierMessage> getList(ProjectSupplierMessage projectSupplierMessage);
}
