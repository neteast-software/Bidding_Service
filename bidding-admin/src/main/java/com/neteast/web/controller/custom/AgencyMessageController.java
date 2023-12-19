package com.neteast.web.controller.custom;

import com.neteast.business.service.IAgencyMessageService;
import com.neteast.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author lzp
 * @date 2023年12月19 11:03
 */

@Controller
@RequestMapping("/agencyMessage")
public class AgencyMessageController extends BaseController{

    @Resource
    IAgencyMessageService agencyMessageService;




}
