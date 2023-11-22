package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ProjectBidding;
import com.neteast.business.domain.project.vo.ProjectBiddingVO;
import com.neteast.business.service.IProjectBiddingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @GetMapping("/list")
    public AjaxResult getProjectBiddingList(ProjectBiddingVO projectBiddingVO){

        startPage();
        PageDomain pageDomain = new PageDomain();
        List<ProjectBiddingVO> list = projectBiddingService.getProjectBiddingList(projectBiddingVO);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addProjectBiddingData(@RequestBody ProjectBidding projectBidding){
        projectBiddingService.save(projectBidding);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectBiddingData(@PathVariable Integer id){
        projectBiddingService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateProjectBiddingData(@RequestBody ProjectBidding projectBidding){
        projectBiddingService.updateById(projectBidding);
        return success();
    }
}
