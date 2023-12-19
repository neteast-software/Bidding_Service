package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.AgencyMessage;
import com.neteast.business.mapper.AgencyMessageMapper;
import com.neteast.business.service.IAgencyMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月19 11:01
 */

@Service
public class AgencyMessageServiceImpl extends ServiceImpl<AgencyMessageMapper, AgencyMessage> implements IAgencyMessageService {

    @Resource
    AgencyMessageMapper agencyMessageMapper;

    @Override
    public List<AgencyMessage> getAgencyMessageList(AgencyMessage agencyMessage) {
        return agencyMessageMapper.getList(agencyMessage);
    }
}
