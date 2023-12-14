package com.neteast.web.controller.template;

import com.neteast.business.service.ITemplateFileService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 模板文件处理
 * @author lzp
 * @date 2023年12月13 18:26
 */

@RestController
@RequestMapping("/templateFile")
public class TemplateFileController extends BaseController {

    @Resource
    ITemplateFileService templateFileService;

    @GetMapping("/list")
    public AjaxResult getTemplateFileList(){

        return null;
    }




}
