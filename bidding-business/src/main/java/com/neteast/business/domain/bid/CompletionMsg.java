package com.neteast.business.domain.bid;

import lombok.Data;

/**
 * 专家的一次操作后返回给主持人端的数据
 * @author lzp
 * @date 2023年12月27 18:40
 */

@Data
public class CompletionMsg {

    /** 该专家当前评分项完成情况 */
    private int num;

    /** 供应商id */
    private int supplierId;

    /** 专家id */
    private int userId;

    /** 评分项id */
    private int itemId;

    /** 评分项类型 */
    private String itemType;

    /** 该供应商的该评分项的一致性 */
    private boolean consistent;

    /** 该供应商是否被淘汰 */
    private boolean out;
}
