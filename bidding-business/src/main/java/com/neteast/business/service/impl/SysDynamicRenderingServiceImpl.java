package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.rendering.SysDynamicRendering;
import com.neteast.business.mapper.SysDynamicRenderingMapper;
import com.neteast.business.service.ISysDynamicRenderingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 10:26
 */

@Service
public class SysDynamicRenderingServiceImpl extends ServiceImpl<SysDynamicRenderingMapper, SysDynamicRendering> implements ISysDynamicRenderingService {

    @Resource
    SysDynamicRenderingMapper renderingMapper;

    @Override
    public List<SysDynamicRendering> getSysDynamicRenderingList(SysDynamicRendering sysDynamicRendering) {
        return null;
    }
}
