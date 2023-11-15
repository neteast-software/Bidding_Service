package com.neteast.web.controller.business;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.ProjectInformation;
import com.neteast.business.domain.ProjectTypeInformation;
import com.neteast.business.service.IProjectInformationService;
import com.neteast.business.service.IProjectTypeInformationService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:51
 */

@RestController
@RequestMapping("/projectTypeInformation")
public class ProjectTypeInformationController extends BaseController {

    @Resource
    IProjectTypeInformationService projectTypeInformationService;

    @GetMapping("/list")
    public AjaxResult getProjectTypeInformationList(ProjectInformation projectInformation){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectTypeInformation> list = projectTypeInformationService.list();
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

}
