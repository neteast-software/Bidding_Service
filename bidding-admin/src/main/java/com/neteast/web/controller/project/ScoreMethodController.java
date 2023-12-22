package com.neteast.web.controller.project;

import com.neteast.business.domain.project.ScoreMethod;
import com.neteast.business.domain.template.ItemTemplate;
import com.neteast.business.service.IItemTemplateService;
import com.neteast.business.service.IScoreMethodService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
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


    @GetMapping("/list")
    public AjaxResult getScoreMethodList(ScoreMethod scoreMethod){

        List<ScoreMethod> list = scoreMethodService.getScoreMethodList(scoreMethod);
        return success(list);
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
