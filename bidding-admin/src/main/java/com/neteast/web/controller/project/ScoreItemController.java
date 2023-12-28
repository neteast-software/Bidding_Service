package com.neteast.web.controller.project;

import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.domain.project.vo.PackageInformationVO;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.domain.project.vo.ProjectScoreItemVO;
import com.neteast.business.domain.project.vo.ScoreItemVO;
import com.neteast.business.service.IScoreItemService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public AjaxResult addScoreItem(@RequestBody ProjectScoreItemVO itemVO){

        List<ScoreItemVO> projectScoreItems = itemVO.getItems();
        List<ScoreItem> list = new ArrayList<>();
        projectScoreItems.forEach(l->{
            String itemType = l.getItemType();
            ScoreItem item;
            switch (itemType){
                case "conform":
                    item = ScoreItemVO.toConform(l);
                    list.add(item);
                    break;
                case "business":
                    item = ScoreItemVO.toBusiness(l);
                    list.add(item);
                    break;
                case "technical":
                    item = ScoreItemVO.toTech(l);
                    list.add(item);
                    break;
                case "price":
                    item = ScoreItemVO.toPrice(l);
                    list.add(item);
                    break;
                case "qualification":
                    item = ScoreItemVO.toQualification(l);
                    list.add(item);
                    break;
            }
        });
        list.forEach(l->{
            scoreItemService.addScoreItem(l);
        });
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateScoreItem(@RequestBody ScoreItem scoreItem){

        scoreItemService.updateScoreItem(scoreItem);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delScoreItem(@PathVariable("id")Integer id){

        scoreItemService.removeScoreItem(id);
        return success();
    }

    @GetMapping("/list")
    public AjaxResult getScoreItem(ScoreItem scoreItem){
        List<ScoreItem> list = scoreItemService.getListByExtId(scoreItem.getExtId());
        return success(list);
    }

}
