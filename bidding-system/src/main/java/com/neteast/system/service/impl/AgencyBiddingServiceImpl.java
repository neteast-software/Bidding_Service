package com.neteast.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.system.domain.bidding.AgencyBidding;
import com.neteast.system.mapper.AgencyBiddingMapper;
import com.neteast.system.service.IAgencyBiddingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lzp
 * @date 2023年11月14 13:45
 */

@Service
public class AgencyBiddingServiceImpl extends ServiceImpl<AgencyBiddingMapper, AgencyBidding> implements IAgencyBiddingService {
}
