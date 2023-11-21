package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.FailBiddingMsg;
import com.neteast.business.domain.project.vo.FailBiddingMsgVO;
import com.neteast.business.mapper.FailBiddingMsgMapper;
import org.springframework.stereotype.Service;
import com.neteast.business.service.IFailBiddingMsgService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:56
 */

@Service
public class FailBiddingMsgServiceImpl extends ServiceImpl<FailBiddingMsgMapper, FailBiddingMsg> implements IFailBiddingMsgService {

    @Resource
    FailBiddingMsgMapper failBiddingMsgMapper;

    @Override
    public List<FailBiddingMsgVO> getProjectBiddingMsgList(FailBiddingMsgVO failBiddingMsgVO) {
        return failBiddingMsgMapper.getList(failBiddingMsgVO);
    }

    @Override
    public boolean addProjectBiddingMsgData(FailBiddingMsg failBiddingMsg) {
        return save(failBiddingMsg);
    }
}
