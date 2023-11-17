package com.neteast.business.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.custom.AgencyBidding;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月14 13:44
 */
public interface IAgencyBiddingService extends IService<AgencyBidding> {

    List<AgencyBidding> getAgencyBiddingData(AgencyBidding agencyBidding);
}
