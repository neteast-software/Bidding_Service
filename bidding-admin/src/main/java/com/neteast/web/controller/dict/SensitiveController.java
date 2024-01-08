package com.neteast.web.controller.dict;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.dict.Sensitive;
import com.neteast.business.domain.dict.vo.SensitiveVO;
import com.neteast.business.service.ISensitiveService;
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
 * 敏感词管理
 * @author lzp
 * @date 2023年12月15 11:48
 */

@RestController
@RequestMapping("/sensitive")
public class SensitiveController extends BaseController {

    @Resource
    ISensitiveService sensitiveService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/listByPage")
    public AjaxResult getSensitiveListByPage(SensitiveVO sensitiveVO){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<SensitiveVO> list = sensitiveService.getSensitiveList(sensitiveVO);
        TableDataInfo info = getDataTable(list);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","sensitive","list");
        initPageParams(rendering,info, pageDomain.getPageSize(), pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/list")
    public AjaxResult getSensitiveList(SensitiveVO sensitiveVO){

        List<SensitiveVO> list = sensitiveService.getSensitiveList(sensitiveVO);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addSensitive(@RequestBody SensitiveVO sensitiveVO){

        Sensitive sensitive = SensitiveVO.convert(sensitiveVO);
        sensitiveService.save(sensitive);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateSensitive(@RequestBody SensitiveVO sensitiveVO){

        Sensitive sensitive = SensitiveVO.convert(sensitiveVO);
        sensitiveService.updateById(sensitive);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delSensitive(@PathVariable("id")Integer id){

        sensitiveService.removeById(id);
        return success();
    }

}
