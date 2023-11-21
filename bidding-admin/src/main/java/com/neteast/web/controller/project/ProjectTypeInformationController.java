package com.neteast.web.controller.project;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.business.domain.project.vo.ProjectTypeInformationVO;
import com.neteast.business.service.ProjectTypeInformationService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目类型相关信息
 * @author lzp
 * @date 2023年11月15 11:51
 */

@RestController
@RequestMapping("/projectTypeInformation")
public class ProjectTypeInformationController extends BaseController {

    @Resource
    ProjectTypeInformationService projectTypeInformationService;

    @GetMapping("/list")
    public AjaxResult getProjectTypeInformationList(ProjectTypeInformationVO projectTypeInformationVO){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectTypeInformationVO> list = projectTypeInformationService.getProjectTypeInfoList(projectTypeInformationVO);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

}
