package com.neteast.web.controller.project;

import com.neteast.business.domain.project.ProjectType;
import com.neteast.business.service.IProjectTypeService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
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
    public AjaxResult getProjectTypeList(ProjectType projectType){
        List<ProjectType> list = projectTypeService.getProjectTypeList(projectType);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addProjectType(@RequestBody ProjectType projectType){

        projectTypeService.save(projectType);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectType(@PathVariable("id") Integer id){
        ProjectType projectType = projectTypeService.getById(id);
        if (projectType!=null){
            projectType.setDel(0);
            projectTypeService.updateById(projectType);
        }
        return error("无该项目类型");
    }

    @PostMapping("/update")
    public AjaxResult updateProjectType(@RequestBody ProjectType projectType){
        projectTypeService.updateById(projectType);
        return success();
    }

}
