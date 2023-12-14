package com.neteast.business.domain.bid;

import lombok.Data;

import java.util.List;

/**
 * 项目招投标的评审的整体情况
 * @author lzp
 * @date 2023年12月11 9:45
 */

@Data
public class ProjectBidMsg {

    /** 项目id */
    private Integer projectId;

    /** 项目名称 */
    private String projectName;

    /** 各供应商评审信息 */
    private List<SupplierBidMsg> supplierBidMsg;

}
