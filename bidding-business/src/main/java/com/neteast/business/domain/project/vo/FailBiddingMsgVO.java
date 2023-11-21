package com.neteast.business.domain.project.vo;

import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月21 10:57
 */
@Data
public class FailBiddingMsgVO {

    /** 主键id */
    private Integer id ;

    /** 项目id */
    private Integer extProjectId ;

    /** 流标原因 **/
    private String failReason;

    /** 项目编号 */
    private String projectCode ;

    /** 备案编号 **/
    private String filingsNumber;

    /** 项目名称 */
    private String projectName ;

    /** 采购类型(项目类型) */
    private String procureType ;
}
