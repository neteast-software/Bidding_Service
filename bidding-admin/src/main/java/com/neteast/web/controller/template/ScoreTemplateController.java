package com.neteast.web.controller.template;

import com.neteast.business.service.IScoreTemplateService;
import com.neteast.common.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 评分项子项模板
 * @author lzp
 * @date 2023年12月15 18:43
 */

@RestController
@RequestMapping("/scoreTemplate")
public class ScoreTemplateController extends BaseController {

    @Resource
    IScoreTemplateService scoreTemplateService;

}
