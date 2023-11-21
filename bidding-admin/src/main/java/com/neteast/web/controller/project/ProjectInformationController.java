package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.service.IPackageInformationService;
import com.neteast.business.service.IProjectInformationService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @GetMapping("/list")
    public AjaxResult getProjectInformationList(ProjectInformation projectInformation){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectInformation> list = projectInformationService.getProjectInformationList(projectInformation);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/one")
    public AjaxResult getProjectInformationOne(ProjectInformation projectInformation){
        ProjectInformation data = projectInformationService.getById(projectInformation);
        ProjectInformationVO projectInformationVO = ProjectInformationVO.convert(data);
        List<PackageInformation> temp = packageInformationService.lambdaQuery().eq(PackageInformation::getProjectaId,projectInformation.getId()).list();
        projectInformationVO.setPackageInformationList(temp);
        return success(projectInformationVO);
    }

    @PostMapping("/add")
    public AjaxResult addProjectInformationData(@RequestBody ProjectInformation projectInformation){
        projectInformationService.addProjectInformation(projectInformation);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateProjectInformationData(@RequestBody ProjectInformationVO projectInformationVO){
        projectInformationService.updateProjectInformation(projectInformationVO);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectInformationData(@PathVariable Integer id){
        ProjectInformation projectInformation = projectInformationService.getById(id);
        projectInformation.setProjectDel(0);
        projectInformationService.updateById(projectInformation);
        return success();
    }

}
