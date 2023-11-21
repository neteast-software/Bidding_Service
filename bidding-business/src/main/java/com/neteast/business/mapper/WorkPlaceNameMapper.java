package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.custom.WorkPlaceName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 18:27
 */

@Mapper
public interface WorkPlaceNameMapper extends BaseMapper<WorkPlaceName> {

    List<WorkPlaceName> getList(WorkPlaceName workPlaceName);
}