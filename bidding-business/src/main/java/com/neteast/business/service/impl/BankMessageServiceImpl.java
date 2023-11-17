package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.BankMessage;
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
    public List<BankMessage> getBankMessageByType(BankMessage bankMessage) {

        LambdaQueryChainWrapper<BankMessage> wrapper = lambdaQuery().eq(BankMessage::getType,bankMessage.getType())
                .eq(BankMessage::getExtId,bankMessage.getType());
        if (bankMessage.getUse()!=null){
            return wrapper.eq(BankMessage::getUse,bankMessage.getUse()).list();
        }
        return wrapper.list();
    }

    @Override
    public List<BankMessage> getBankMessageList(BankMessage bankMessage) {
        return bankMessageMapper.getList(bankMessage);
    }
}
