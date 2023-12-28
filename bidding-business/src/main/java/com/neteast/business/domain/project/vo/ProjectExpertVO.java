package com.neteast.business.domain.project.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lzp
 * @date 2023年11月20 17:22
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectExpertVO {

    /** 项目id */
    private Integer projectId ;

    /** 分包id */
    private Integer packageId;

    /** 专家id */
    private Integer expertId ;

    /** 专家名称 */
    private String name;

    /** 身份证号 */
    private String identityNumber;

    /** 所属单位 */
    private String workAddr;

    /** 联系方式 */
    private String contractPhone;
}
