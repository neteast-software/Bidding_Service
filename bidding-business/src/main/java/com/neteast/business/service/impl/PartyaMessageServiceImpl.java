package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.mapper.PartyaMessageMapper;
import com.neteast.business.domain.PartyaMessage;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IPartyaMessageService;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:55
 */

@Service
public class PartyaMessageServiceImpl extends ServiceImpl<PartyaMessageMapper, PartyaMessage> implements IPartyaMessageService {

    @Override
    public List<PartyaMessage> getPartyAMessageList(PartyaMessage partyaMessage) {
        return list();
    }
}
