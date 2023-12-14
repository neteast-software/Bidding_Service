package com.neteast.business.domain.bid;

import lombok.Data;

/**
 * 某一个评审的子项的完成情况
 * 如(价格性评审的子项)
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