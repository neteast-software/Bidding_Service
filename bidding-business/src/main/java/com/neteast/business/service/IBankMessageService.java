package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.custom.BankMessage;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:53
 */
public interface IBankMessageService extends IService<BankMessage> {

    /**
     * @Description 获取对象下的银行卡信息
     * @author lzp
     * @Date 2023/11/17
     */
    List<BankMessage> getBankMessageByType(BankMessage bankMessage);

    /**
     * @Description
     * @author lzp
     * @Date 2023/11/17
     */
    List<BankMessage> getBankMessageList(BankMessage bankMessage);
}
