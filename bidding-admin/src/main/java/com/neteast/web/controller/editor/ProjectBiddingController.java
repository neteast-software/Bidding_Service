package com.neteast.web.controller.editor;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.editor.ProjectBidding;
import com.neteast.business.domain.editor.ProjectFileContent;
import com.neteast.business.domain.editor.vo.ProjectBiddingVO;
import com.neteast.business.domain.project.vo.ProjectFileMsgVO;
import com.neteast.business.service.IProjectBiddingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

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
    public AjaxResult getProjectBiddingList(ProjectBiddingVO projectBidding){

        List<ProjectBiddingVO> res = projectBiddingService.getProjectBiddingList(projectBidding);
        //Map<Integer,List<ProjectBidding>> res = list.stream().collect(Collectors.groupingBy(ProjectBidding::getStage));
        return success(res);
    }

    @GetMapping("/getListByPage")
    public AjaxResult getProjectBiddingListByPage(ProjectBiddingVO projectBidding){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectBiddingVO> list = projectBiddingService.getProjectBiddingList(projectBidding);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
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
    public AjaxResult updateProjectBiddingData(@RequestBody ProjectBiddingVO projectBiddingVO){
        ProjectBidding projectBidding = ProjectBiddingVO.convert(projectBiddingVO);
        projectBiddingService.updateById(projectBidding);
        return success();
    }

    @PostMapping("/save")
    public AjaxResult saveProjectBiddingFile(@RequestBody ProjectFileContent fileContent) throws IOException {
        //项目文件保存
        ProjectBidding projectBidding = projectBiddingService.getById(fileContent.getId());
        String path = projectBidding.getFilePath();
        String content = fileContent.getContent();
        FileUtil.writeUtf8String(content,new File(path));
        //todo 敏感词判断
        return success();
    }

    @GetMapping("/content/{id}")
    public AjaxResult getProjectBiddingContent(@PathVariable("id")Integer id) throws IOException {

        ProjectBidding projectBidding = projectBiddingService.getById(id);
        String path = projectBidding.getFilePath();
        File file = new File(path);
        String content = FileUtil.readUtf8String(file);
        ProjectFileContent fileContent = ProjectFileContent.convert(projectBidding);
        fileContent.setContent(content);
        return success(fileContent);
    }
}
