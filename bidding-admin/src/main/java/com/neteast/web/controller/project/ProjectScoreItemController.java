package com.neteast.web.controller.project;

import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.service.IProjectScoreItemService;
import com.neteast.business.service.IScoreItemService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月12 14:25
 */

@RestController
@RequestMapping("/projectScoreItem")
public class ProjectScoreItemController extends BaseController {

    @Resource
    IProjectScoreItemService projectScoreItemService;

    @Resource
    IScoreItemService scoreItemService;

    @GetMapping("/getListByProject")
    public AjaxResult getProjectScoreItemByProject(ProjectScoreItem projectScoreItem){

        List<ProjectScoreItem> list = projectScoreItemService.getProjectScoreItemList(projectScoreItem);
        return success(list);
    }

    @GetMapping("/getOne")
    public AjaxResult getProjectScoreItem(ProjectScoreItem projectScoreItem){
        List<ProjectScoreItem> list = projectScoreItemService.getProjectScoreItemList(projectScoreItem);
        if (list.size()!=0){
            ProjectScoreItem item = list.get(0);
            List<ScoreItem> scoreItems = scoreItemService.getListByExtId(item.getId());
            item.setScoreItems(scoreItems);
            return success(scoreItems);
        }
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectScoreItem(@PathVariable("id") Integer id){
        boolean res = projectScoreItemService.removeProjectScoreItem(id);
        return success(res);
    }

    @PostMapping("/add")
    public AjaxResult addProjectScoreItem(@RequestBody ProjectScoreItem projectScoreItem){
        boolean res = projectScoreItemService.save(projectScoreItem);
        return success(res);
    }

    @PostMapping("/update")
    public AjaxResult updateProjectScoreItem(@RequestBody ProjectScoreItem projectScoreItem){
        boolean res = projectScoreItemService.updateById(projectScoreItem);
        return success(res);
    }
}