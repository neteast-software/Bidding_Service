package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 联系人方式
 * @author lzp
 * @date 2023年11月17 11:42
 */

@Data
@Builder
@TableName("contract_message")
@NoArgsConstructor
@AllArgsConstructor
public class ContractMessage extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 单位信息id */
    @TableField("ext_id")
    private Integer extId;

    /** 联系人名称 */
    @TableField("person_name")
    private String personName;

    /** 电话联系方式 */
    @TableField("contract_phone")
    private String contractPhone;

    /** 邮箱 */
    @TableField("mail")
    private String mail;
}
