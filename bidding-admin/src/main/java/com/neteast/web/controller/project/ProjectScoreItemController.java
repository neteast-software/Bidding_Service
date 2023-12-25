package com.neteast.web.controller.project;

import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItem;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.service.IProjectScoreItemService;
import com.neteast.business.service.IScoreItemService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.domain.R;
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
        list.forEach(l->{
            List<ScoreItem> scoreItems = scoreItemService.getListByExtId(l.getId());
            l.setScoreItems(scoreItems);
        });
        return success(list);
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
