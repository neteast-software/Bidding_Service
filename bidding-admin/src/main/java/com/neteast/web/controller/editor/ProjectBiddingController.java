package com.neteast.web.controller.editor;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.editor.ProjectBidding;
import com.neteast.business.domain.project.vo.ProjectBiddingVO;
import com.neteast.business.service.IProjectBiddingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.utils.file.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 甲方项目下的所需文件
 * @author lzp
 * @date 2023年11月20 18:03
 */

@RestController
@RequestMapping("/projectBidding")
public class ProjectBiddingController extends BaseController {

    @Resource
    IProjectBiddingService projectBiddingService;

    @GetMapping("/getList")
    public AjaxResult getProjectBiddingList(ProjectBidding projectBidding){

        List<ProjectBidding> list = projectBiddingService.getProjectBiddingList(projectBidding);
        Map<Integer,List<ProjectBidding>> res = list.stream().collect(Collectors.groupingBy(ProjectBidding::getStage));
        return success(res);
    }

    @PostMapping("/add")
    public AjaxResult addProjectBiddingData(@RequestBody ProjectBidding projectBidding) throws IOException {
        projectBiddingService.creatProjectBiddingFile(projectBidding);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectBiddingData(@PathVariable Integer id){
        projectBiddingService.delProjectBiddingFile(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateProjectBiddingData(@RequestBody ProjectBidding projectBidding){
        projectBiddingService.updateById(projectBidding);
        return success();
    }

    @PostMapping("/save")
    public AjaxResult saveProjectBiddingFile(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) throws IOException {
        //项目文件保存
        ProjectBidding projectBidding = projectBiddingService.getById(id);
        String path = projectBidding.getFilePath();
        file.transferTo(new File(path));
        return success();
    }

    @GetMapping("/content/{id}")
    public AjaxResult getProjectBiddingContent(@PathVariable("id")Integer id) throws IOException {
        ProjectBidding projectBidding = projectBiddingService.getById(id);
        String path = projectBidding.getFilePath();
        File file = new File(path);
        String content = FileUtil.readUtf8String(file);
        return success(content);
    }
}
