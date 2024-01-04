package com.neteast.business.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.rendering.SysDynamicRendering;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 10:26
 */
public interface ISysDynamicRenderingService extends IService<SysDynamicRendering> {

    List<SysDynamicRendering> getSysDynamicRenderingList(SysDynamicRendering sysDynamicRendering);

    JSONObject getSysDynamicRendering(String module, String page, String method);
}
