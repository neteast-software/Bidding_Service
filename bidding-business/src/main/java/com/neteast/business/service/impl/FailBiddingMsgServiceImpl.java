package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.FailBiddingMsg;
import com.neteast.business.mapper.FailBiddingMsgMapper;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IFailBiddingMsgService;

import java.util.Date;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:56
 */

@Service
public class FailBiddingMsgServiceImpl extends ServiceImpl<FailBiddingMsgMapper, FailBiddingMsg> implements IFailBiddingMsgService {

    @Override
    public List<FailBiddingMsg> getProjectBiddingMsgList(FailBiddingMsg failBiddingMsg) {
        return lambdaQuery().eq(FailBiddingMsg::getExtProjectId, failBiddingMsg.getExtProjectId())
                .orderByDesc(FailBiddingMsg::getCreateTime).list();
    }

    @Override
    public boolean addProjectBiddingMsgData(FailBiddingMsg failBiddingMsg) {
        return save(failBiddingMsg);
    }
}
