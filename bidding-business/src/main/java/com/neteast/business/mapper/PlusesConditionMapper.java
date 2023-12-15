package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.dict.PlusesCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 项目加分项
 * @author lzp
 * @date 2023年12月15 10:43
 */

@Mapper
public interface PlusesConditionMapper extends BaseMapper<PlusesCondition> {

    List<PlusesCondition> getList(PlusesCondition plusesCondition);

}
