package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.custom.ExpertMessage;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:54
 */
public interface IExpertMessageService extends IService<ExpertMessage> {

    List<ExpertMessage> getExpertMessageList(ExpertMessage expertMessage);
}
