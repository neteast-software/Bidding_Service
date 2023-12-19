package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.custom.AgencyMessage;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月19 11:00
 */
public interface IAgencyMessageService extends IService<AgencyMessage> {

    List<AgencyMessage> getAgencyMessageList(AgencyMessage agencyMessage);
}
