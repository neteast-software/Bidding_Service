package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 甲方项目信息
 * @author lzp
 * @date 2023年11月14 10:51
 */

@Data
@TableName("project_message")
public class PartyaMessage extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 公司名称 */
    @TableField("company_name")
    private String companyName;

    /** 公司所在地 */
    @TableField("company_address")
    private String companyAddress;

    /** 公司联系方式 */
    @TableField("company_contact")
    private String companyContact;

    /** 公司类型 */
    @TableField("company_type")
    private String companyType;

    /** 公司法人 */
    @TableField("legal_person")
    private String legalPerson;

    /** 项目编号 */
    private String projectCode;

    /** 备案编号 */
    private String filingsNumber;

    /** 项目类型 */
    private Integer projectType;

    /** 项目名称 */
    private String projectName;
    /** 采购方式 */
    private String procureType;

    /** 项目状态 */
    private Integer projectStatus;

    /** 招标时间 */
    private Date inviteBidding;

    /** 开标时间 */
    private Date openBidding;

    /** 项目删除 */
    private Integer projectDel;

}
