package com.neteast.business.domain.bid;

import lombok.Data;

/**
 * 专家的如(商务性评审)的整体完成情况
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
}
