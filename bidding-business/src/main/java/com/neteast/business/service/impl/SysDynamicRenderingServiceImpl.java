package com.neteast.business.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.rendering.SysDynamicRendering;
import com.neteast.business.mapper.SysDynamicRenderingMapper;
import com.neteast.business.service.ISysDynamicRenderingService;
import com.neteast.common.exception.BaseBusException;
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
        return renderingMapper.getList(sysDynamicRendering);
    }

    @Override
    public JSONObject getSysDynamicRendering(String module, String page, String method) {
        SysDynamicRendering sysDynamicRendering = this.lambdaQuery().eq(SysDynamicRendering::getModule,module).eq(SysDynamicRendering::getPage,page)
                .eq(SysDynamicRendering::getMethod,method).one();
        if (sysDynamicRendering==null){
            throw new BaseBusException(1003,"动态表单不存在");
        }
        String rendering = sysDynamicRendering.getRendering();
        return JSON.parseObject(rendering);
    }
}
