package com.neteast.web.controller.project;

import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.ProjectStage;
import com.neteast.business.domain.project.ProjectStatus;
import com.neteast.business.domain.project.vo.ProjectStepCompletionVO;
import com.neteast.business.service.IProjectInformationService;
import com.neteast.business.service.IProjectStageService;
import com.neteast.business.service.IProjectStatusService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lzp
 * @date 2023年12月25 14:45
 */

@RestController
@RequestMapping("/projectStatus")
public class ProjectStatusController extends BaseController {

    @Resource
    IProjectStatusService projectStatusService;

    @Resource
    IProjectStageService projectStageService;

    @Resource
    IProjectInformationService projectInformationService;

    @GetMapping("/getStatus/{id}")
    public AjaxResult getProjectStatus(@PathVariable("id") Integer id){

        ProjectInformation information = projectInformationService.getById(id);
        if(information!=null){
            //项目当前已处理阶段
            List<ProjectStatus> statuses = projectStatusService.lambdaQuery().eq(ProjectStatus::getProjectId,id).list();
            Map<Integer,ProjectStatus> stageMap = statuses.stream().collect(Collectors.toMap(ProjectStatus::getStageId,a->a,(k1,k2)->k1));
            //项目类型阶段
            List<ProjectStage> stages = projectStageService.getProjectStageListById(information.getProjectTypeId());
            List<ProjectStepCompletionVO> voList = new ArrayList<>();
            stages.forEach(s->{
                ProjectStepCompletionVO vo = new ProjectStepCompletionVO();
                vo.setProjectTypeId(s.getProjectTypeId());
                vo.setStepNum(s.getStepNum());
                vo.setTemplateId(s.getTemplateId());
                vo.setTemplateName(s.getTemplateName());
                vo.setStepName(s.getStepName());
                ProjectStatus status = stageMap.get(s.getId());
                if (status==null){
                    vo.setDone(false);
                }else {
                    vo.setStageTime(status.getStageTime());
                    vo.setDone(true);
                }
                voList.add(vo);
            });
            return success(voList);
        }
        return error("不存在该项目");
    }

}
