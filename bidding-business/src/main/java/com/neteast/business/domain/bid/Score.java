package com.neteast.business.domain.bid;

import lombok.Data;

/**
 * 得分
 * @author lzp
 * @date 2023年12月12 13:36
 */

@Data
public class Score {

    /** 评审项id */
    private Integer id;

    /** 类型 1-选择 2-分值 */
    private Integer type;

    /** 选择值 */
    private int choose = -1;

    /** 分数值 */
    private Double value;
}
