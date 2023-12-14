package com.neteast.web.controller.project;

import com.neteast.business.domain.bid.Score;
import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.service.IScoreItemService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        boolean res = scoreItemService.addScoreItem(scoreItem);
        return success(res);
    }

    @PostMapping("/update")
    public AjaxResult updateScoreItem(@RequestBody ScoreItem scoreItem){
        return success(scoreItemService.updateById(scoreItem));
    }

    @PostMapping("/del/{id}")
    public AjaxResult delScoreItem(@RequestBody ScoreItem scoreItem){
        return success(scoreItemService.removeById(scoreItem));
    }

    @GetMapping("/list")
    public AjaxResult getScoreItem(ScoreItem scoreItem){
        List<ScoreItem> list = scoreItemService.getListByExtId(scoreItem.getExtId());
        return success(list);
    }

}
