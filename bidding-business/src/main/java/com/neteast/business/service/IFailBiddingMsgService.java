package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.FailBiddingMsg;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:53
 */
public interface IFailBiddingMsgService extends IService<FailBiddingMsg> {

    /**
     * 获取招标历史信息
     * @param failBiddingMsg
     * @return
     */
    List<FailBiddingMsg> getProjectBiddingMsgList(FailBiddingMsg failBiddingMsg);

    /**
     * @Description 添加招标信息
     * @author lzp
     * @Date 2023/11/15
     */
    boolean addProjectBiddingMsgData(FailBiddingMsg failBiddingMsg);

}
