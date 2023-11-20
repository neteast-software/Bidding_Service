package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.vo.BankMessageVO;
import com.neteast.business.mapper.BankMessageMapper;
import com.neteast.business.service.IBankMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:57
 */

@Service
public class BankMessageServiceImpl extends ServiceImpl<BankMessageMapper, BankMessage> implements IBankMessageService {

    @Resource
    BankMessageMapper bankMessageMapper;

    @Override
    public List<BankMessage> getBankMessageByExId(BankMessage bankMessage) {

        LambdaQueryChainWrapper<BankMessage> wrapper = lambdaQuery().eq(BankMessage::getExtId,bankMessage.getExtId());
        if (bankMessage.getUse()!=null){
            return wrapper.eq(BankMessage::getUse,bankMessage.getUse()).list();
        }
        return wrapper.list();
    }

    @Override
    public List<BankMessageVO> getBankMessageList(BankMessageVO bankMessageVO) {
        return bankMessageMapper.getList(bankMessageVO);
    }
}
