package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.SupplierInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月11 18:06
 */

@Mapper
public interface SupplierInformationMapper extends BaseMapper<SupplierInformation> {

    List<SupplierInformation> getList(SupplierInformation supplierInformation);
}
