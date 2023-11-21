package com.neteast.business.domain.project.vo;

import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月21 9:45
 */

@Data
public class AttachMessageVO {

    /** 主键id */
    private Integer id;

    /** 附件名称 */
    private String name;

    /** 附件地址url */
    private String address;

    /** 关联项目id */
    private Integer projectId;

    /** 关联分包id */
    private Integer packageId ;

    /** 文件类型(如开标文件) */
    private String fileType;

    /** 附件类型 */
    private String type;

    /** 附件大小 */
    private Long size;

    /** 下载次数 */
    private Integer num;

    /** 项目编号 */
    private String projectCode ;

    /** 备案编号 **/
    private String filingsNumber;

    /** 项目名称 */
    private String projectName ;

    /** 合同号 */
    private String contractPackage;

    /** 品目号 */
    private String packageNum;

    /** 采购标的 */
    private String biddingSubject;
}
