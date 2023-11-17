package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.mapper.ContractMessageMapper;
import com.neteast.business.service.IContractMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:58
 */

@Service
public class ContractMessageServiceImpl extends ServiceImpl<ContractMessageMapper, ContractMessage> implements IContractMessageService {

    @Override
    public List<ContractMessage> getContractMessageByType(ContractMessage contractMessage) {
        return lambdaQuery().eq(ContractMessage::getType,contractMessage.getType())
                .eq(ContractMessage::getExtId,contractMessage.getExtId()).list();
    }


}
