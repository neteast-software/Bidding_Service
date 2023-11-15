package com.neteast.web.controller.business;

import com.neteast.common.core.controller.BaseController;
import org.springframework.web.bind.annotation.RestController;
import service.IAgencyBiddingService;

import javax.annotation.Resource;

/**
 * @author lzp
 * @date 2023年11月15 11:36
 */

@RestController
public class testController extends BaseController {

    @Resource
    IAgencyBiddingService agencyBiddingService;

}
