package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ProjectExpert;
import com.neteast.business.domain.project.vo.ProjectExpertVO;
import com.neteast.business.service.IProjectExpertService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.common.utils.PageUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目专家信息
 * @author lzp
 * @date 2023年11月20 17:13
 */

@RestController
@RequestMapping("/projectExpert")
public class ProjectExpertController extends BaseController {

    @Resource
    IProjectExpertService projectExpertService;

    @GetMapping("/list")
    public AjaxResult getProjectExpertList(ProjectExpertVO projectExpertVO){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectExpertVO> list = projectExpertService.getProjectExpertList(projectExpertVO);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addProjectExpertData(@RequestBody ProjectExpert projectExpert){
        projectExpertService.save(projectExpert);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectExpertData(@PathVariable Integer id){
        projectExpertService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateProjectExpertData(@RequestBody ProjectExpert projectExpert){
        projectExpertService.updateById(projectExpert);
        return success();
    }

}
