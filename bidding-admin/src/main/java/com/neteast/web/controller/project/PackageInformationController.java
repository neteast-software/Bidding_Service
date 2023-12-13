package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.PackageInformation;
import com.neteast.business.service.IPackageInformationService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @GetMapping("/list")
    public AjaxResult getPackageInformationList(PackageInformation packageInformation){
        startPage();
        PageDomain pageDomain = new PageDomain();
        List<PackageInformation> list = packageInformationService.lambdaQuery().eq(PackageInformation::getProjectId,packageInformation.getProjectId()).list();
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addPackageInformationData(@RequestBody PackageInformation packageInformation){
        packageInformationService.save(packageInformation);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delPackageInformationData(@PathVariable Integer id){
        packageInformationService.removeById(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updatePackageInformationData(@RequestBody PackageInformation packageInformation){
        packageInformationService.updateById(packageInformation);
        return success();
    }

}
