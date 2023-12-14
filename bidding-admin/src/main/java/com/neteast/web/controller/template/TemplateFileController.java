package com.neteast.web.controller.template;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.template.TemplateFile;
import com.neteast.business.domain.template.vo.TemplateFileVO;
import com.neteast.business.service.ITemplateFileService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @GetMapping("/listByPage")
    public AjaxResult getTemplateFileListByPage(TemplateFileVO templateFileVO){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<TemplateFileVO> list = templateFileService.getTemplateFileList(templateFileVO);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/list")
    public AjaxResult getTemplateFileList(TemplateFileVO templateFile){

        List<TemplateFileVO> templateFiles = templateFileService.getTemplateFileList(templateFile);
        return success(templateFiles);
    }

    @PostMapping("/add")
    public AjaxResult addTemplateFile(@RequestBody TemplateFileVO templateFile) throws IOException {

        templateFileService.createTemplateFile(templateFile);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delTemplateFile(@PathVariable("id")Integer id){

        templateFileService.delTemplateFile(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateTemplateFile(@RequestBody TemplateFileVO templateFileVO){

        templateFileService.saveTemplateFile(templateFileVO);
        return success();
    }

    @GetMapping("/content/{id}")
    public AjaxResult getTemplateFileContent(@PathVariable("id") Integer id){

        TemplateFile templateFile = templateFileService.getById(id);
        String filePath = templateFile.getFilePath();
        File file = new File(filePath);
        String content = FileUtil.readUtf8String(file);
        JSONObject body = new JSONObject();
        body.put("content",content);
        return success(body);
    }

}
