package com.neteast.business.domain.bid;

import lombok.Data;

/**
 * 价格评审
 * @author lzp
 * @date 2023年12月08 17:23
 */

@Data
public class Price {

    /** 评审项id */
    private Integer id;

    private double value;
}
