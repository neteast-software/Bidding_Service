package com.neteast.business.domain.bid;

import lombok.Data;

/**
 * 评标阶段
 * @author lzp
 * @date 2023年12月14 9:44
 */

@Data
public class BidStage {

    /** 评审阶段id */
    String stageId;

    /** 评审阶段名称 */
    String stageName;
}
