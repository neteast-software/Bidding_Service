package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.dict.DictType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典类型
 * @author lzp
 * @date 2023年12月19 18:13
 */

@Mapper
public interface DictTypeMapper extends BaseMapper<DictType> {
}
