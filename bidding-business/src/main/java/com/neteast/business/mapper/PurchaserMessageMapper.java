package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.custom.PurchaserMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月19 10:43
 */

@Mapper
public interface PurchaserMessageMapper extends BaseMapper<PurchaserMessage> {

    List<PurchaserMessage> getList(PurchaserMessage purchaserMessage);
}
