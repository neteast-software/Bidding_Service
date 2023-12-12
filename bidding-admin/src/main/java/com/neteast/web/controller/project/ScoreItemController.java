package com.neteast.web.controller.project;

import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.service.IScoreItemService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lzp
 * @date 2023年12月12 14:48
 */

@RestController
@RequestMapping("/scoreItem")
public class ScoreItemController extends BaseController{

    @Resource
    IScoreItemService scoreItemService;

    @PostMapping("/add")
    public AjaxResult addScoreItem(@RequestBody ScoreItem scoreItem){
       return success(scoreItemService.save(scoreItem));
    }

    @PostMapping("/update")
    public AjaxResult updateScoreItem(@RequestBody ScoreItem scoreItem){
        return success(scoreItemService.updateById(scoreItem));
    }

    @PostMapping("/del/{id}")
    public AjaxResult delScoreItem(@RequestBody ScoreItem scoreItem){
        return success(scoreItemService.removeById(scoreItem));
    }

}
