package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.mapper.BankMessageMapper;
import com.neteast.business.service.IBankMessageService;
import org.springframework.stereotype.Service;

/**
 * @author lzp
 * @date 2023年11月17 13:57
 */

@Service
public class BankMessageServiceImpl extends ServiceImpl<BankMessageMapper, BankMessage> implements IBankMessageService {
}
