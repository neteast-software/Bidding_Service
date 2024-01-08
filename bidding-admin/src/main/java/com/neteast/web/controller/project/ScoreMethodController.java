package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ScoreMethod;
import com.neteast.business.domain.template.ItemTemplate;
import com.neteast.business.service.IItemTemplateService;
import com.neteast.business.service.IScoreMethodService;
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
 * @author lzp
 * @date 2023年12月22 11:44
 */

@RestController
@RequestMapping("/scoreMethod")
public class ScoreMethodController extends BaseController {

    @Resource
    IScoreMethodService scoreMethodService;

    @Resource
    IItemTemplateService itemTemplateService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/list")
    public AjaxResult getScoreMethodList(ScoreMethod scoreMethod){

        List<ScoreMethod> list = scoreMethodService.getScoreMethodList(scoreMethod);
        return success(list);
    }

    @GetMapping("/listByPage")
    public AjaxResult getScoreMethodListByPage(ScoreMethod scoreMethod){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ScoreMethod> list = scoreMethodService.getScoreMethodList(scoreMethod);
        TableDataInfo info = getDataTable(list);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","scoreMethod","list");
        initPageParams(rendering,info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/getOne/{id}")
    public AjaxResult getScoreMethodDetail(@PathVariable("id")Integer id){

        ScoreMethod scoreMethod = scoreMethodService.getById(id);
        if (scoreMethod!=null){
            List<ItemTemplate> list = itemTemplateService.getItemTemplateListById(scoreMethod.getId());
            scoreMethod.setItemTemplates(list);
            return success(scoreMethod);
        }
        return error("不存在该评分方式");
    }
    @PostMapping("/add")
    public AjaxResult addScoreMethod(@RequestBody ScoreMethod scoreMethod){

        scoreMethodService.save(scoreMethod);
        return addSuccess();
    }

    @PostMapping("/update")
    public AjaxResult updateScoreMethod(@RequestBody ScoreMethod scoreMethod){

        scoreMethodService.updateById(scoreMethod);
        return updateSuccess();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delScoreMethod(@PathVariable("id")String id){

        scoreMethodService.removeById(id);
        return delSuccess();
    }

}
