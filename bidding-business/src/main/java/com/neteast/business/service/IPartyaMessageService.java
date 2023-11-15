package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.PartyaMessage;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:52
 */
public interface IPartyaMessageService extends IService<PartyaMessage> {

    List<PartyaMessage> getPartyAMessageList(PartyaMessage partyaMessage);
}
