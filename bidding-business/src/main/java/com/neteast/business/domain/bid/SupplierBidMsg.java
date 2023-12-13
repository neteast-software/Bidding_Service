package com.neteast.business.domain.bid;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 供应商评审时状态
 * @author lzp
 * @date 2023年12月08 17:10
 */

@Data
public class SupplierBidMsg {

    /** 供应商id */
    private Integer supplierId;

    /** 供应商名称 */
    private String supplierName;

    /** 项目id */
    private Integer projectId;

    /** 供应商得分情况 */
    private List<TotalScore> totalScores = new ArrayList<>();

    public void setTotalScores(TotalScore totalScore){
        totalScores.add(totalScore);
    }
}
