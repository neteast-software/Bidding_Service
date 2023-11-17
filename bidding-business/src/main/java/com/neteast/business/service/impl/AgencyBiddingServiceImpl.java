package com.neteast.business.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.mapper.AgencyBiddingMapper;
import com.neteast.business.domain.custom.AgentMessage;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IAgencyBiddingService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月14 13:45
 */

@Service
public class AgencyBiddingServiceImpl extends ServiceImpl<AgencyBiddingMapper, AgentMessage> implements IAgencyBiddingService {

    @Resource
    AgencyBiddingMapper agencyBiddingMapper;

    @Override
    public List<AgentMessage> getAgencyBiddingData(AgentMessage agentMessage) {
        return list();
    }
}
