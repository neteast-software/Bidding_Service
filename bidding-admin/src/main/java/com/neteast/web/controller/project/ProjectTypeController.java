package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ProjectType;
import com.neteast.business.domain.project.vo.ProjectTypeVO;
import com.neteast.business.service.IProjectTypeService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月18 16:25
 */

@RestController
@RequestMapping("/projectType")
public class ProjectTypeController extends BaseController {

    @Resource
    IProjectTypeService projectTypeService;

    @GetMapping("/list")
    public AjaxResult getProjectTypeList(ProjectTypeVO projectTypeVO){
        List<ProjectTypeVO> list = projectTypeService.getProjectTypeList(projectTypeVO);
        return success(list);
    }

    @GetMapping("/listByPage")
    public AjaxResult getProjectTypeListByPage(ProjectTypeVO projectTypeVO){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectTypeVO> list = projectTypeService.getProjectTypeList(projectTypeVO);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addProjectType(@RequestBody ProjectTypeVO projectTypeVO){

        ProjectType projectType = ProjectTypeVO.convert(projectTypeVO);
        projectTypeService.save(projectType);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectType(@PathVariable("id") Integer id){
        ProjectType projectType = projectTypeService.getById(id);
        if (projectType!=null){
            projectType.setDel(0);
            projectTypeService.updateById(projectType);
            return success();
        }
        return error("无该项目类型");
    }

    @PostMapping("/update")
    public AjaxResult updateProjectType(@RequestBody ProjectTypeVO projectTypeVO){
        ProjectType projectType = ProjectTypeVO.convert(projectTypeVO);
        projectTypeService.updateById(projectType);
        return success();
    }

}
