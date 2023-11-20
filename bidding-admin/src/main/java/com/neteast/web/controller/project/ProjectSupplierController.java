package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ProjectSupplier;
import com.neteast.business.domain.project.vo.ProjectSupplierVO;
import com.neteast.business.service.IProjectSupplierService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目中标信息
 * @author lzp
 * @date 2023年11月20 16:14
 */

@RestController
@RequestMapping("/projectSupplier")
public class ProjectSupplierController extends BaseController {

    @Resource
    IProjectSupplierService projectSupplierService;

    @GetMapping("/list")
    public AjaxResult getProjectSupplierList(ProjectSupplierVO projectSupplierVO){
        startPage();
        PageDomain pageDomain = new PageDomain();
        List<ProjectSupplierVO> list = projectSupplierService.getProjectSupplierList(projectSupplierVO);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addProjectSupplierData(@RequestBody ProjectSupplier projectSupplier){
        projectSupplierService.save(projectSupplier);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delProjectSupplierData(@PathVariable Integer id){
        projectSupplierService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateProjectSupplierData(@RequestBody ProjectSupplier projectSupplier){
        projectSupplierService.updateById(projectSupplier);
        return success();
    }

}
