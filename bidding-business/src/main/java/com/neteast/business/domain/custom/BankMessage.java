package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 银行卡信息
 * @author lzp
 * @date 2023年11月17 11:39
 */

@Data
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
    @TableField("cardName")
    private String cardName;

    /** 银行卡归属;1-甲方公司、2-供应商、3-代理商 */
    @TableField("type")
    private Integer type;

    /** 关联表id */
    @TableField("extId")
    private Integer extId;

    /** 用途(如保证金账户等) */
    @TableField("use")
    private String use;
}
