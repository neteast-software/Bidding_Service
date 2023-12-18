package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 专家信息
 * @author lzp
 * @date 2023年11月17 11:40
 */

@Data
@TableName("expert_message")
public class ExpertMessage extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 联系方式 */
    @TableField("contract_phone")
    private String contractPhone;

    /** 行业 */
    @TableField("industry")
    private String industry;

    /** 职业 */
    @TableField("occupation")
    private String occupation;

    /** 职称 */
    @TableField("titles")
    private String titles;

    /** 所属单位 */
    @TableField("work_addr")
    private String workAddr;

    /** 专家名称 */
    @TableField("name")
    private String name;

    /** 身份证号 */
    @TableField("identity_number")
    private String identityNumber;

    /** 年龄 */
    @TableField("age")
    private Integer age;

    /** 地址 */
    @TableField("address")
    private String address;

}
