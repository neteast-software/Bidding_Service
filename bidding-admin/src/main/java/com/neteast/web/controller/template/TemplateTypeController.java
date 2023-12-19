package com.neteast.web.controller.template;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.template.TemplateType;
import com.neteast.business.domain.template.vo.TemplateTypeVO;
import com.neteast.business.mapper.TemplateTypeMapper;
import com.neteast.business.service.ITemplateTypeService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.common.utils.PageUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模板类型处理
 * @author lzp
 * @date 2023年12月13 18:25
 */

@RestController
@RequestMapping("/templateType")
public class TemplateTypeController extends BaseController {

    @Resource
    ITemplateTypeService templateTypeService;

    @GetMapping("/listByPage")
    public AjaxResult getTemplateTypeListByPage(TemplateTypeVO templateType){

        //startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        PageUtils.startPage(pageDomain.getPageNum(),pageDomain.getPageSize());
        List<TemplateTypeVO> list = templateTypeService.getTemplateTypeList(templateType);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/list")
    public AjaxResult getTemplateTypeList(TemplateTypeVO templateType){
        List<TemplateTypeVO> templateTypes = templateTypeService.getTemplateTypeList(templateType);
        return success(templateTypes);
    }

    @PostMapping("/add")
    public AjaxResult addTemplateType(@RequestBody TemplateTypeVO templateTypeVO){
        TemplateType templateType = TemplateTypeVO.convert(templateTypeVO);
        templateTypeService.save(templateType);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateTemplateType(@RequestBody TemplateTypeVO templateTypeVO){
        TemplateType templateType = TemplateTypeVO.convert(templateTypeVO);
        templateTypeService.updateById(templateType);
        return updateSuccess();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delTemplateType(@PathVariable("id")Integer id){
        templateTypeService.delTemplateType(id);
        return delSuccess();
    }
}
