package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.project.FailBiddingMsg;
import com.neteast.business.domain.project.vo.FailBiddingMsgVO;

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
    List<FailBiddingMsgVO> getProjectBiddingMsgList(FailBiddingMsgVO failBiddingMsgVO);

    /**
     * @Description 添加招标信息
     * @author lzp
     * @Date 2023/11/15
     */
    boolean addProjectBiddingMsgData(FailBiddingMsg failBiddingMsg);

}
