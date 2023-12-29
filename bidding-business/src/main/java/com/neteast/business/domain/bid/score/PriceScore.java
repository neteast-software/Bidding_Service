package com.neteast.business.domain.bid.score;

import lombok.Data;

import java.util.List;

/**
 * 价格分
 * @author lzp
 * @date 2023年12月27 9:57
 */

@Data
public class PriceScore{

    /** 评分项id */
    private Integer itemId;

    /** 评分项类型 */
    private String itemType;

    /** 价格分得分 */
    private Double value;
}
