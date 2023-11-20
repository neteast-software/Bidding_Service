package com.neteast.business.domain.project.vo;

import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月20 16:35
 */

@Data
public class ProjectSupplierVO {

    /** 主键id */
    private Integer id;

    /** 项目id */
    private Integer projectId;

    /** 分包id */
    private Integer packageId;

    /** 供应商名称 */
    private String name;

    /** 供应商地址 */
    private String address;

    /** 中标金额 */
    private Double money;

    /** 项目名称 */
    private String projectName;

    /** 项目编号 */
    private String projectCode;

    /** 合同号 */
    private String contractPackage;

    /** 品目号 */
    private String packageNum;

    /** 采购标的 */
    private String biddingSubject;
}
