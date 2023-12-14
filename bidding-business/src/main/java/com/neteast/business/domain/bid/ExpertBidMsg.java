package com.neteast.business.domain.bid;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家评标记录
 * @author lzp
 * @date 2023年12月08 18:19
 */

@Data
public class ExpertBidMsg {

    /** 专家id */
    private Integer id;

    /** 专家名称 */
    private String name;

    /** 供应商id */
    private Integer supplierId;

    /** 分包id */
    private Integer packageId;

    /** 评审情况 */
    private List<Score> reviewStatus = new ArrayList<>();

    public void setReviewStatus(Score score){
        reviewStatus.add(score);
    }
}
