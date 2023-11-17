package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.custom.SupplierMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 17:16
 */

@Mapper
public interface SupplierMessageMapper extends BaseMapper<SupplierMessage> {

    List<SupplierMessage> getList(SupplierMessage supplierMessage);
}
