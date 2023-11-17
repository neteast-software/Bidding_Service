package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.ExpertMessage;
import com.neteast.business.mapper.ExpertMessageMapper;
import com.neteast.business.service.IExpertMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:59
 */

@Service
public class ExpertMessageServiceImpl extends ServiceImpl<ExpertMessageMapper, ExpertMessage> implements IExpertMessageService {

    @Resource
    ExpertMessageMapper expertMessageMapper;

    @Override
    public List<ExpertMessage> getExpertMessageList(ExpertMessage expertMessage) {
        return expertMessageMapper.getList(expertMessage);
    }
}
