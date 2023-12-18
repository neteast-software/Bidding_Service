package com.neteast.web.controller.project;

import com.neteast.business.domain.project.ProjectCondition;
import com.neteast.business.service.IProjectPlusConditionService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 项目分包的附加条件项处理
 * @author lzp
 * @date 2023年12月18 14:11
 */

@RestController
@RequestMapping("/projectCondition")
public class ProjectConditionController extends BaseController {

    @Resource
    IProjectPlusConditionService conditionService;



}
