package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ProjectRuleCondition;
import com.neteast.business.service.IProjectRuleConditionService;
import com.neteast.business.service.ISysDynamicRenderingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目规则条件管理
 * @author lzp
 * @date 2024年01月04 11:18
 */

@RestController
@RequestMapping("/projectRuleCondition")
public class ProjectRuleConditionController extends BaseController {

    @Resource
    IProjectRuleConditionService ruleConditionService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/listByPage")
    public AjaxResult getProjectRuleConditionListByPage(ProjectRuleCondition projectRuleCondition){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectRuleCondition> conditions = ruleConditionService.getProjectRuleConditionList(projectRuleCondition);
        TableDataInfo info = getDataTable(conditions);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","projectRuleCondition","list");
        initPageParams(rendering,info,pageDomain.getPageSize(), pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/toModify/{id}")
    public AjaxResult toModify(@PathVariable("id")Integer id){

        ProjectRuleCondition condition = ruleConditionService.getById(id);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","projectRuleCondition","toModify");
        rendering.put("data",condition);
        return success(rendering);
    }

    @GetMapping("/toAdd")
    public AjaxResult toAdd(){

        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","projectRuleCondition","toAdd");
        return success(rendering);
    }

    @PostMapping("/add")
    public AjaxResult addProjectRuleCondition(@RequestBody ProjectRuleCondition ruleCondition){

        ruleConditionService.save(ruleCondition);
        return addSuccess();
    }

    @PostMapping("/update")
    public AjaxResult updateProjectRuleCondition(@RequestBody ProjectRuleCondition ruleCondition){

        ruleConditionService.updateById(ruleCondition);
        return updateSuccess();
    }

    @DeleteMapping("{id}")
    public AjaxResult delProjectRuleCondition(@RequestBody ProjectRuleCondition ruleCondition){

        ruleConditionService.removeById(ruleCondition);
        return delSuccess();
    }

}
