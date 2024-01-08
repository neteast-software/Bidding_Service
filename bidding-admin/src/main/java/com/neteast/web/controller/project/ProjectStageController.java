package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.ProjectStage;
import com.neteast.business.domain.project.ProjectStatus;
import com.neteast.business.domain.project.vo.ProjectStageVO;
import com.neteast.business.domain.project.vo.ProjectStepStatusVO;
import com.neteast.business.service.IProjectInformationService;
import com.neteast.business.service.IProjectStageService;
import com.neteast.business.service.IProjectStatusService;
import com.neteast.business.service.ISysDynamicRenderingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    IProjectStatusService statusService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/listByPage")
    public AjaxResult getProjectStageListByPage(ProjectStage projectStage){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectStage> list = stageService.getProjectStageList(projectStage);
        TableDataInfo info = getDataTable(list);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","projectStage","list");
        initPageParams(rendering,info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/getProjectStep/{id}")
    public AjaxResult getProjectStepList(@PathVariable(value = "id",required = false) Integer projectId){

        if (projectId==null){
            return error("未携带项目id参数");
        }
        ProjectInformation information = informationService.getById(projectId);
        //获取项目类型
        Integer typeId = information.getProjectTypeId();
        //获取项目步骤
        List<ProjectStage> list = stageService.getProjectStageListById(typeId);
        //获取项目当前阶段情况
        List<ProjectStatus> statuses = statusService.getProjectStatusListById(projectId);
        Map<Integer,ProjectStatus> map = statuses.stream().collect(Collectors.toMap(ProjectStatus::getStageId, a1->a1,(k1, k2)->k1));
        List<ProjectStageVO> voList = new ArrayList<>();
        list.forEach(l->{
            ProjectStageVO vo = ProjectStageVO.convert(l);
            ProjectStatus status = map.get(l.getId());
            if (status!=null){
                vo.setNum(status.getNum());
            }
            voList.add(vo);
        });
        return success(voList);
    }

    @PostMapping("/add")
    public AjaxResult addProjectStep(@RequestBody ProjectStage projectStage){

        stageService.addProjectStage(projectStage);
        return addSuccess();
    }

    @PostMapping("/update")
    public AjaxResult updateProjectStep(@RequestBody ProjectStage projectStage){

        stageService.updateProjectStage(projectStage);
        return updateSuccess();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectStep(@PathVariable("id")Integer id){

        stageService.delProjectStage(id);
        return delSuccess();
    }

}
