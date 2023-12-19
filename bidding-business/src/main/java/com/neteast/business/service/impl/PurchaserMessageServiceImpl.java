package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.custom.PurchaserMessage;
import com.neteast.business.mapper.PurchaserMessageMapper;
import com.neteast.business.service.IPurchaserMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月19 11:01
 */
@Service
public class PurchaserMessageServiceImpl extends ServiceImpl<PurchaserMessageMapper, PurchaserMessage> implements IPurchaserMessageService {

    @Resource
    PurchaserMessageMapper purchaserMessageMapper;

    @Override
    public List<PurchaserMessage> getPurchaserMessageList(PurchaserMessage purchaserMessage) {
        return purchaserMessageMapper.getList(purchaserMessage);
    }
}
