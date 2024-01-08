package com.neteast.web.controller.template;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.template.ItemTemplate;
import com.neteast.business.domain.template.vo.ItemTemplateVO;
import com.neteast.business.service.IItemTemplateService;
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
 * 评分项模板
 * @author lzp
 * @date 2023年12月15 18:43
 */

@RestController
@RequestMapping("/itemTemplate")
public class ItemTemplateController extends BaseController {

    @Resource
    IItemTemplateService itemTemplateService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/list")
    public AjaxResult getItemTemplateList(ItemTemplate itemTemplate){

        List<ItemTemplate> list = itemTemplateService.getItemTemplateList(itemTemplate);
        return success(list);
    }

    @GetMapping("/listByPage")
    public AjaxResult getItemTemplateListByPage(ItemTemplate itemTemplate){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ItemTemplate> list = itemTemplateService.getItemTemplateList(itemTemplate);
        TableDataInfo info = getDataTable(list);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","itemTemplate","list");
        initPageParams(rendering,info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/toAdd")
    public AjaxResult toAdd(){

        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","itemTemplate","toAdd");
        return success(rendering);
    }

    @GetMapping("/toModify/{id}")
    public AjaxResult toModify(@PathVariable("id")Integer id){

        ItemTemplate itemTemplate = itemTemplateService.getById(id);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","itemTemplate","toModify");
        rendering.put("data",itemTemplate);
        return success(rendering);
    }

    @PostMapping("/add")
    public AjaxResult addItemTemplate(@RequestBody ItemTemplate itemTemplate){

        itemTemplateService.save(itemTemplate);
        return addSuccess();
    }

    @PostMapping("/update")
    public AjaxResult updateItemTemplate(@RequestBody ItemTemplate itemTemplate){

        itemTemplateService.updateById(itemTemplate);
        return updateSuccess();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delItemTemplate(@PathVariable("id")Integer id){

        itemTemplateService.removeItemTemplate(id);
        return delSuccess();
    }

}
