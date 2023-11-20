package com.neteast.business.domain.project.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月20 18:06
 */

@Data
public class ProjectBiddingVO{

    /** 主键id */
    private String id;

    /** 项目id */
    private Integer projectId;

    /** 模板id */
    private Integer templateId;

    /** 分包id */
    private Integer packageId;

    /** 项目文件类型(如招标文件) */
    private String type;

    /** 阶段(如开标前，开标等) */
    private Integer stage;

    /** 文件名称 */
    private String name;

    /** 文件类型(excel,word) */
    private String fileType;

    /** 下载地址 */
    private String downloadUrl;

    /** 项目编号 */
    private String projectCode ;

    /** 备案编号 **/
    private String filingsNumber;

    /** 项目名称 */
    private String projectName ;

    /** 采购类型(项目类型) */
    private String procureType ;

    /** 合同号 */
    private String contractPackage;

    /** 品目号 */
    private String packageNum;

    /** 采购标的 */
    private String biddingSubject;
}
