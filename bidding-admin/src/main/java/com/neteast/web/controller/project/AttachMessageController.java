package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.vo.AttachMessageVO;
import com.neteast.business.service.IAttachMessageService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月21 9:41
 */

@RestController
@RequestMapping("/attachMessage")
public class AttachMessageController extends BaseController {

    @Resource
    IAttachMessageService attachMessageService;

    @Value("${ruoyi.profile}")
    String filePath;

    @GetMapping("/list")
    public AjaxResult gatAttachMessageList(AttachMessageVO attachMessageVO){

        startPage();
        PageDomain pageDomain = new PageDomain();
        List<AttachMessageVO> list = attachMessageService.getAttachMessageList(attachMessageVO);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/wholeByProject")
    public AjaxResult getAttachMessageWholeByProject(AttachMessageVO attachMessageVO){

        List<AttachMessageVO> list = attachMessageService.getAttachMessageList(attachMessageVO);
        return success(list);
    }
    @PostMapping("/upload")
    public AjaxResult uploadAttachMessage(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value = "packageId",required = false) Integer packageId,
            @RequestParam(value = "fileType",required = false) String fileType,
            @RequestParam(value = "file")MultipartFile file) throws IOException {

        String filename = attachMessageService.saveAttachMessage(projectId,packageId,fileType,file);
        file.transferTo(new File(filePath+filename));
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delAttachMessage(@PathVariable Integer id){

        attachMessageService.removeById(id);
        return success();
    }
}
