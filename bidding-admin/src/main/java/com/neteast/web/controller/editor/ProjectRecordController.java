package com.neteast.web.controller.editor;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.editor.ProjectRecord;
import com.neteast.business.service.IProjectRecordService;
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
 * @date 2023年12月25 10:48
 */

@RestController
@RequestMapping("/projectRecord")
public class ProjectRecordController extends BaseController {

    @Resource
    IProjectRecordService recordService;

    @GetMapping("/listByPage")
    public AjaxResult getProjectRecordListByPage(ProjectRecord projectRecord){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<ProjectRecord> list = recordService.getProjectRecordList(projectRecord);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }
}
