package com.neteast.web.controller.custom;

import com.neteast.business.service.IPurchaserMessageService;
import com.neteast.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author lzp
 * @date 2023年12月19 11:02
 */

@Controller
@RequestMapping("/purchaserMessage")
public class PurchaserMessageController extends BaseController {

    @Resource
    IPurchaserMessageService purchaserMessageService;



}
