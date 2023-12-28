package com.neteast.business.domain.project.vo;

import com.neteast.business.domain.bid.SupplierBidMsg;
import com.neteast.business.domain.project.SupplierInformation;
import lombok.Data;

/**
 * 主持人端的供应商信息
 * @author lzp
 * @date 2023年12月28 16:07
 */

@Data
public class HostSupplierVO {

    /** 供应商id */
    private Integer supplierId;

    /** 供应商名称 */
    private String supplierName;

    public static HostSupplierVO convert(SupplierInformation information){
        HostSupplierVO vo = new HostSupplierVO();
        vo.setSupplierId(information.getId());
        vo.setSupplierName(information.getName());
        return vo;
    }

}
