package com.neteast.web.controller.editor;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.editor.ProjectBidding;
import com.neteast.business.domain.editor.ProjectFileContent;
import com.neteast.business.domain.editor.vo.ProjectBiddingVO;
import com.neteast.business.domain.project.vo.ProjectFileMsgVO;
import com.neteast.business.service.IProjectBiddingService;
import com.neteast.business.service.IProjectInformationService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.common.exception.BaseBusException;
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
    public AjaxResult addProjectBiddingData(@RequestBody ProjectBiddingVO projectBiddingVO) throws IOException {

        ProjectBidding projectBidding = ProjectBiddingVO.convert(projectBiddingVO);
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
        projectBiddingService.updateProjectBiddingFile(projectBidding);
        return success();
    }

    @PostMapping("/save")
    public AjaxResult saveProjectBiddingFile(@RequestBody ProjectFileContent fileContent) throws IOException {
        projectBiddingService.saveProjectBiddingFile(fileContent);
        return success();
    }

    @GetMapping("/content/{id}")
    public AjaxResult getProjectBiddingContent(@PathVariable("id")Integer id) throws IOException {

        ProjectBidding projectBidding = projectBiddingService.getById(id);
        if (projectBidding==null){
            throw new BaseBusException(500,"该项目文件不存在");
        }
        String path = projectBidding.getFilePath();
        File file = new File(path);
        String content = FileUtil.readUtf8String(file);
        ProjectFileContent fileContent = ProjectFileContent.convert(projectBidding);
        fileContent.setContent(content);
        return success(fileContent);
    }
}
