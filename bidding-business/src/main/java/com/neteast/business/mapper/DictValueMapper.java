package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.dict.DictValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月22 10:37
 */

@Mapper
public interface DictValueMapper extends BaseMapper<DictValue> {

    List<DictValue> getList(DictValue dictValue);
}
