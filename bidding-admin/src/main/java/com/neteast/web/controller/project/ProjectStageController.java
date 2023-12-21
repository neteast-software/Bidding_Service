package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.ProjectStage;
import com.neteast.business.service.IProjectInformationService;
import com.neteast.business.service.IProjectStageService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目步骤管理
 * @author lzp
 * @date 2023年12月21 15:46
 */

@RestController
@RequestMapping("/projectStage")
public class ProjectStageController extends BaseController {

    @Resource
    IProjectStageService stageService;

    @Resource
    IProjectInformationService informationService;

    @GetMapping("/listByPage")
    public AjaxResult getProjectStageListByPage(ProjectStage projectStage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectStage> list = stageService.getProjectStageList(projectStage);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/getProjectStep/{id}")
    public AjaxResult getProjectStepList(@PathVariable("id") Integer projectId){

        ProjectInformation information = informationService.getById(projectId);
        //获取项目类型
        Integer typeId = information.getProjectTypeId();
        //获取项目步骤
        List<ProjectStage> list = stageService.getProjectStageListById(typeId);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addProjectStep(@RequestBody ProjectStage projectStage){

        stageService.save(projectStage);
        return addSuccess();
    }

    @PostMapping("/update")
    public AjaxResult updateProjectStep(@RequestBody ProjectStage projectStage){

        stageService.updateById(projectStage);
        return updateSuccess();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectStep(@PathVariable("id")Integer id){

        stageService.removeById(id);
        return delSuccess();
    }

}
