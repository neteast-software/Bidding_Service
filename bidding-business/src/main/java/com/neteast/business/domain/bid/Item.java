package com.neteast.business.domain.bid;

import lombok.Data;

/**
 * @author lzp
 * @date 2023年12月13 11:25
 */
@Data
public class Item{

    /** 评审项id */
    private Integer id;

    /** 选择值 */
    private Boolean choose;

    /** 分数值 */
    private Double value;
}