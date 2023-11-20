package com.neteast.business.domain.project.vo;

import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月20 17:22
 */

@Data
public class ProjectExpertVO {

    /** 主键id */
    private Integer id ;

    /** 项目id */
    private Integer projectId ;

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

    /** 项目编号 */
    private String projectCode ;

    /** 备案编号 **/
    private String filingsNumber;

    /** 项目名称 */
    private String projectName ;
}
