package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 采购人信息
 * @author lzp
 * @date 2023年12月19 10:29
 */

@Data
@TableName("purchaser_message")
public class PurchaserMessage extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("address")
    private String address;

    @TableField("phone")
    private String phone;

    @TableField("contract_person")
    private String contractPerson;
}
