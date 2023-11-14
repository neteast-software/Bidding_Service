package com.neteast.web.controller.bidding;

import com.neteast.system.domain.bidding.AgencyBidding;
import com.neteast.system.service.IAgencyBiddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月14 13:46
 */

@RestController
@RequestMapping("/test")
public class testController {

    @Resource
    private IAgencyBiddingService agencyBiddingService;

    @GetMapping("/agencyBidding")
    public void testAgencyBidding(){

        List<AgencyBidding> list = agencyBiddingService.list();
        System.out.println(list);
    }

}
