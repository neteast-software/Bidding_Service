package com.neteast.business.domain.project.vo;

import lombok.Data;

/**
 * 项目类别信息
 * @author lzp
 * @date 2023年11月20 15:29
 */

@Data
public class ProjectTypeInformationVO {

    /** 项目id */
    private Integer projectId;

    /** 甲方id */
    private Integer partyaId;

    /** 项目名称 */
    private String projectName;

    /** 项目行业 */
    private String projectType;

    /** 项目编号 */
    private String projectCode;

    /** 采购方式 */
    private String procureType;

    /** 甲方单位名称 */
    private String name;

    /** 甲方单位地址 */
    private String address;

}
