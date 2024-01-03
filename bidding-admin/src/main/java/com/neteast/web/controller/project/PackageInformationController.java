package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.*;
import com.neteast.business.domain.project.vo.*;
import com.neteast.business.service.*;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.common.utils.PageUtils;
import io.swagger.models.auth.In;
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
    ISupplierInformationService supplierInformationService;

    @Resource
    IProjectInformationService projectInformationService;

    @Resource
    IProjectScoreItemService scoreItemService;

    @Resource
    IProjectExpertService projectExpertService;

    @Resource
    IScoreItemRuleService scoreItemRuleService;

    @GetMapping("/list")
    public AjaxResult getPackageInformationList(PackageInformation packageInformation){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        ProjectInformation project = projectInformationService.getById(packageInformation.getProjectId());
        if (project==null){
            return error("不存在该项目");
        }
        List<PackageInformation> list = packageInformationService.lambdaQuery().eq(PackageInformation::getProjectId,packageInformation.getProjectId()).list();
        List<PackageInformationVO> voList = new ArrayList<>();
        list.forEach(l->{
            PackageInformationVO vo = PackageInformationVO.convert(l);
            //评分项内容
            List<ProjectScoreItem> scoreItems = scoreItemService.getProjectScoreItemList(l.getProjectId(),l.getId());
            List<ProjectScoreVO> vos = new ArrayList<>();
            scoreItems.forEach(s->{
                ScoreItemRule temp = ScoreItemRule.builder().projectType(project.getProjectTypeId()).scoreMethod(l.getScoreId())
                        .tradeMethod(project.getTradeType()).itemType(s.getItemType()).specialCondition(project.getSpecialCondition()).build();
                ScoreItemRule rule = scoreItemRuleService.getScoreItemRole(temp);
                ProjectScoreVO scoreVO = ProjectScoreVO.convert(s,rule);
                vos.add(scoreVO);
            });
            vo.setScoreItems(vos);
            //附加项内容
            List<ProjectCondition> conditions = projectPlusConditionService.lambdaQuery().eq(ProjectCondition::getPackageId,l.getId())
                    .eq(ProjectCondition::getProjectId,l.getProjectId()).list();
            vo.setConditions(conditions);
            voList.add(vo);
        });
        TableDataInfo info = getDataTable(voList);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/getOne/{id}")
    public AjaxResult getPackageInformationOne(@PathVariable("id")Integer id){

        PackageInformation information = packageInformationService.getById(id);
        Integer packageId = information.getId();
        Integer projectId = information.getProjectId();
        ProjectInformation project = projectInformationService.getById(projectId);
        PackageInformationVO informationVO = PackageInformationVO.convert(information);
        //附加项内容
        List<ProjectCondition> conditions = projectPlusConditionService.lambdaQuery().eq(ProjectCondition::getPackageId,packageId)
                .eq(ProjectCondition::getProjectId,projectId).list();
        informationVO.setConditions(conditions);
        //评分项内容
        List<ProjectScoreItem> scoreItems = scoreItemService.getProjectScoreItemList(projectId,packageId);
        List<ProjectScoreVO> vos = new ArrayList<>();
        scoreItems.forEach(s->{
            ScoreItemRule temp = ScoreItemRule.builder().projectType(project.getProjectTypeId()).scoreMethod(informationVO.getScoreId())
                    .tradeMethod(project.getTradeType()).itemType(s.getItemType()).specialCondition(project.getSpecialCondition()).build();
            ScoreItemRule rule = scoreItemRuleService.getScoreItemRole(temp);
            ProjectScoreVO scoreVO = ProjectScoreVO.convert(s,rule);
            vos.add(scoreVO);
        });
        informationVO.setScoreItems(vos);
        return success(informationVO);
    }

    /**
     * @Description 主持人端的界面信息展示
     * @author lzp
     * @Date 2023/12/28
     */
    @GetMapping("/showHost")
    public AjaxResult getShowHostMsg(Integer packageId,Integer projectId){

        ShowHostMsgVO showHostMsgVO = new ShowHostMsgVO();

        //获取该分包的供应商信息
        List<SupplierInformation> suppliers = supplierInformationService.getList(projectId,packageId);
        List<HostSupplierVO> suppliersVO = new ArrayList<>();
        suppliers.forEach(s->{
            HostSupplierVO vo1 = HostSupplierVO.convert(s);
            suppliersVO.add(vo1);
        });
        showHostMsgVO.setSuppliers(suppliersVO);

        //获取分包的专家信息
        ProjectExpertVO vo = ProjectExpertVO.builder().packageId(packageId).projectId(projectId).build();
        List<ProjectExpertVO> experts = projectExpertService.getProjectExpertList(vo);
        List<HostExpertVO> expertsVO = new ArrayList<>();
        experts.forEach(e->{
            HostExpertVO vo2 = HostExpertVO.convert(e);
            expertsVO.add(vo2);
        });
        showHostMsgVO.setExperts(expertsVO);

        //获取分包的题目信息
        List<ProjectScoreItem> scoreItems = scoreItemService.getProjectScoreItemList(projectId,packageId);
        List<HostTitleVO> scoreItemsVO = new ArrayList<>();
        int num = 0;
        for (ProjectScoreItem item:scoreItems) {
            num = num + item.getNum();
            HostTitleVO vo3 = HostTitleVO.convert(item);
            scoreItemsVO.add(vo3);
        }
        showHostMsgVO.setTitles(scoreItemsVO);

        //设置总题目数
        int total = num*expertsVO.size();
        showHostMsgVO.setTotalTitle(total);
        return success(showHostMsgVO);
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
