package com.neteast.web.controller.template;

import com.neteast.business.mapper.TemplateTypeMapper;
import com.neteast.business.service.ITemplateTypeService;
import com.neteast.common.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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



}
