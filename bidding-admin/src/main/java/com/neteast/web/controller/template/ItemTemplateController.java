package com.neteast.web.controller.template;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.template.ItemTemplate;
import com.neteast.business.service.IItemTemplateService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }



}
