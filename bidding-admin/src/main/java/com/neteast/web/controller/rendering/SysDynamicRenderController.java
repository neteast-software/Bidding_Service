package com.neteast.web.controller.rendering;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.rendering.SysDynamicRendering;
import com.neteast.business.service.ISysDynamicRenderingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月21 10:28
 */

@RestController
@RequestMapping("/sysDynamicRendering")
public class SysDynamicRenderController extends BaseController {

    @Resource
    ISysDynamicRenderingService renderingService;

    @GetMapping("/listByPage")
    public AjaxResult getSysDynamicRenderingListByPage(SysDynamicRendering sysDynamicRendering){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<SysDynamicRendering> list = renderingService.getSysDynamicRenderingList(sysDynamicRendering);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/list")
    public AjaxResult getSysDynamicRenderingList(SysDynamicRendering sysDynamicRendering){

        List<SysDynamicRendering> list = renderingService.getSysDynamicRenderingList(sysDynamicRendering);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addSysDynamicRendering(@RequestBody SysDynamicRendering rendering){

        renderingService.save(rendering);
        return addSuccess();
    }

    @PostMapping("/update")
    public AjaxResult updateSysDynamicRendering(@RequestBody SysDynamicRendering rendering){

        renderingService.updateById(rendering);
        return updateSuccess();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delSysDynamicRendering(@PathVariable("id")Integer id){

        renderingService.removeById(id);
        return delSuccess();
    }
}
