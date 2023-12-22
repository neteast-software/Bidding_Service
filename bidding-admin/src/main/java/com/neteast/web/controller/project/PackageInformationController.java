package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.domain.project.ProjectCondition;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.domain.project.vo.PackageInformationVO;
import com.neteast.business.service.IPackageInformationService;
import com.neteast.business.service.IProjectPlusConditionService;
import com.neteast.business.service.IProjectScoreItemService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.common.utils.PageUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目分包信息
 * @author lzp
 * @date 2023年11月20 15:52
 */

@RestController
@RequestMapping("/packageInformation")
public class PackageInformationController extends BaseController {

    @Resource
    IPackageInformationService packageInformationService;

    @Resource
    IProjectPlusConditionService projectPlusConditionService;

    @Resource
    IProjectScoreItemService scoreItemService;

    @GetMapping("/list")
    public AjaxResult getPackageInformationList(PackageInformation packageInformation){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<PackageInformation> list = packageInformationService.lambdaQuery().eq(PackageInformation::getProjectId,packageInformation.getProjectId()).list();
        List<PackageInformationVO> voList = new ArrayList<>();
        list.forEach(l->{
            PackageInformationVO vo = PackageInformationVO.convert(l);
            List<ProjectScoreItem> scoreItems = scoreItemService.getProjectScoreItemList(l.getProjectId(),l.getId());
            vo.setScoreItems(scoreItems);
            voList.add(vo);
        });
        TableDataInfo info = getDataTable(voList);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/getOne/{id}")
    public AjaxResult getPackageInformationOne(@PathVariable("id")Integer id){

        PackageInformation information = packageInformationService.getById(id);
        PackageInformationVO informationVO = PackageInformationVO.convert(information);
        List<ProjectCondition> conditions = projectPlusConditionService.lambdaQuery().eq(ProjectCondition::getPackageId,id).eq(ProjectCondition::getProjectId,information.getProjectId()).list();
        informationVO.setConditions(conditions);
        return success(informationVO);
    }

    @PostMapping("/add")
    public AjaxResult addPackageInformationData(@RequestBody PackageInformationVO packageInformationVO){
        packageInformationService.savePackageInformation(packageInformationVO);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delPackageInformationData(@PathVariable Integer id){
        packageInformationService.delPackageInformation(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updatePackageInformationData(@RequestBody PackageInformationVO packageInformationVO){
        packageInformationService.updatePackageInformation(packageInformationVO);
        return success();
    }

}
