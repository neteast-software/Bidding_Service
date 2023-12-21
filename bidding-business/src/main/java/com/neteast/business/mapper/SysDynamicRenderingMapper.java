package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.rendering.SysDynamicRendering;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 10:25
 */

@Mapper
public interface SysDynamicRenderingMapper extends BaseMapper<SysDynamicRendering> {

    List<SysDynamicRendering> getList(SysDynamicRendering sysDynamicRendering);
}
