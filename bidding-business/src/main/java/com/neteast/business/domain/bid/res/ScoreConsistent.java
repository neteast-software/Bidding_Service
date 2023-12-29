package com.neteast.business.domain.bid.res;

import lombok.Data;

/**
 * 分数一致性
 * @author lzp
 * @date 2023年12月29 14:11
 */

@Data
public class ScoreConsistent {

    /** 该选择的总数 */
    private Integer num;

    /** 评分项子项的id */
    private Integer itemId;

    /** 评分子项的分数/分值 */
    private Double value;
}
