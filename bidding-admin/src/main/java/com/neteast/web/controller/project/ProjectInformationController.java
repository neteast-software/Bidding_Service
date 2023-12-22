package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.custom.AgencyMessage;
import com.neteast.business.domain.custom.PurchaserMessage;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.ProjectStage;
import com.neteast.business.domain.project.ProjectStatus;
import com.neteast.business.domain.project.vo.PackageInformationVO;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.domain.project.vo.ProjectStepStatusVO;
import com.neteast.business.service.*;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目信息
 * @author lzp
 * @date 2023年11月15 11:50
 */

@RestController
@RequestMapping("/projectInformation")
public class ProjectInformationController extends BaseController {

    @Resource
    IProjectInformationService projectInformationService;

    @Resource
    IPackageInformationService packageInformationService;

    @Resource
    IProjectStageService stageService;

    @Resource
    IProjectStatusService statusService;

    @Resource
    IPurchaserMessageService purchaserMessageService;

    @Resource
    IAgencyMessageService agencyMessageService;

    @GetMapping("/listByPage")
    public AjaxResult getProjectInformationListByPage(ProjectInformation projectInformation){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectInformation> list = projectInformationService.getProjectInformationList(projectInformation);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/listStepByPage")
    public AjaxResult getProjectInformationStepListByPage(ProjectInformation projectInformation){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectInformation> list = projectInformationService.getProjectInformationList(projectInformation);
        List<ProjectStepStatusVO> vo = new ArrayList<>();
        list.forEach(l->{
            ProjectStepStatusVO status = ProjectStepStatusVO.convert(l);
            List<ProjectStage> stages = stageService.getProjectStageListById(l.getProjectTypeId());
            List<ProjectStatus> statuses = statusService.getProjectStatusListById(l.getProjectTypeId());
            if (statuses!=null&&statuses.size()!=0){
                status.setProjectStatus(statuses.get(0).getStageId());
            }
            status.setProjectStages(stages);
            vo.add(status);
        });
        TableDataInfo info = getDataTable(vo);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(), pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/one")
    public AjaxResult getProjectInformationOne(ProjectInformation projectInformation){
        ProjectInformation data = projectInformationService.getById(projectInformation);
        ProjectInformationVO projectInformationVO = ProjectInformationVO.convert(data);
        List<PackageInformationVO> temp = packageInformationService.getPackageInformationVOList(projectInformation.getId());
        projectInformationVO.setPackageInformationList(temp);
        //设置甲方信息
        PurchaserMessage purchaser = purchaserMessageService.getById(data.getPartyaId());
        projectInformationVO.setPurchaserMessage(purchaser);
        //设置代理商信息
        AgencyMessage agency = agencyMessageService.getById(data.getAgencyId());
        projectInformationVO.setAgencyMessage(agency);
        return success(projectInformationVO);
    }

    @PostMapping("/add")
    public AjaxResult addProjectInformationData(@RequestBody ProjectInformationVO projectInformationVO){
        projectInformationService.addProjectInformation(projectInformationVO);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateProjectInformationData(@RequestBody ProjectInformationVO projectInformationVO){
        projectInformationService.updateProjectInformation(projectInformationVO);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectInformationData(@PathVariable Integer id){

        projectInformationService.delProjectInformation(id);
        return success();
    }

}
