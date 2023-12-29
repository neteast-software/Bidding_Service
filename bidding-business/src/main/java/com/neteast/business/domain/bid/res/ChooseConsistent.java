package com.neteast.business.domain.bid.res;

import lombok.Data;

/**
 * 选择一致性
 * @author lzp
 * @date 2023年12月29 14:12
 */

@Data
public class ChooseConsistent {

    /** 总数 */
    private Integer num;

    /** 评分子项id */
    private Integer itemId;

    /** 选择的结果 */
    private Boolean choose;
}
