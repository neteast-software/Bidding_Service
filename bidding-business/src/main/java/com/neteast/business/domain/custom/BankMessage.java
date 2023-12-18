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
 * 银行卡信息
 * @author lzp
 * @date 2023年11月17 11:39
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("bank_message")
public class BankMessage extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 银行名称 */
    @TableField("name")
    private String name;

    /** 银行地址 */
    @TableField("address")
    private String address;

    /** 银行卡号 */
    @TableField("number")
    private String number;

    /** 银行卡账户名称 */
    @TableField("card_name")
    private String cardName;

    /** 单位信息id */
    @TableField("ext_id")
    private Integer extId;

    /** 用途(如保证金账户等) */
    @TableField("use")
    private String use;
}
