package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 联系人方式
 * @author lzp
 * @date 2023年11月17 11:42
 */

@Data
@TableName("contract_message")
public class ContractMessage extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 关联表id */
    @TableField("ext_id")
    private Integer extId;

    /** 联系所属类型;甲方、供应方、招标代理商 */
    @TableField("type")
    private String type;

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
