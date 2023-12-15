package com.neteast.web.controller.template;

import com.neteast.business.service.IItemTemplateService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public AjaxResult getItemTemplateList(){
        return null;
    }

}
