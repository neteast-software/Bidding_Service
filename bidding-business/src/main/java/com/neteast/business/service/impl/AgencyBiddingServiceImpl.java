package com.neteast.business.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.mapper.AgencyBiddingMapper;
import com.neteast.business.domain.AgencyBidding;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IAgencyBiddingService;

/**
 * @author lzp
 * @date 2023年11月14 13:45
 */

@Service
public class AgencyBiddingServiceImpl extends ServiceImpl<AgencyBiddingMapper, AgencyBidding> implements IAgencyBiddingService {
}
