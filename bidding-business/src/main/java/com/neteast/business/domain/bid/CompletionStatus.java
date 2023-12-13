package com.neteast.business.domain.bid;

import lombok.Data;

/**
 * @author lzp
 * @date 2023年12月13 11:52
 */
@Data
public class CompletionStatus{

    /** 专家名称 */
    String name;

    /** 专家id */
    Integer userId;

    /** 供应商id */
    Integer supplierId;

    /** 完成题目数 */
    Integer num;

    /** 该项的分值 */
    Double value;

    /** 该项是否一致通过 */
    boolean pass;
}
