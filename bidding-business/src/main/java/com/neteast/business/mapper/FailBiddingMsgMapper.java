package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.project.FailBiddingMsg;
import com.neteast.business.domain.project.vo.FailBiddingMsgVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月15 11:17
 */

@Mapper
public interface FailBiddingMsgMapper extends BaseMapper<FailBiddingMsg> {

    List<FailBiddingMsgVO> getList(FailBiddingMsgVO failBiddingMsgVO);
}
