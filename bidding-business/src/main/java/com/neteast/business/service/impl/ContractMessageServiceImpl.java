package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.vo.ContractMessageVO;
import com.neteast.business.mapper.ContractMessageMapper;
import com.neteast.business.service.IContractMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:58
 */

@Service
public class ContractMessageServiceImpl extends ServiceImpl<ContractMessageMapper, ContractMessage> implements IContractMessageService {

    @Resource
    ContractMessageMapper contractMessageMapper;

    @Override
    public List<ContractMessage> getContractMessageByExId(ContractMessage contractMessage) {
        return lambdaQuery().eq(ContractMessage::getExtId,contractMessage.getExtId()).list();
    }

    @Override
    public List<ContractMessageVO> getContractMessageList(ContractMessageVO contractMessageVO) {
        return contractMessageMapper.getList(contractMessageVO);
    }
}
