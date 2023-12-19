package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 招标代理人信息
 * @author lzp
 * @date 2023年12月19 10:30
 */

@Data
@TableName("agency_message")
public class AgencyMessage extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 代理商名称 */
    @TableField("name")
    private String name;

    /** 代理商地址 */
    @TableField("address")
    private String address;

    /** 联系人 */
    @TableField("contract_person")
    private String contractPerson;

    /** 电话 */
    @TableField("phone")
    private String phone;

    /** 开户银行 */
    @TableField("bank_name")
    private String bankName;

    /** 银行账号 */
    @TableField("bank_account")
    private String bankAccount;

    @Override
    public String toString() {
        return "AgencyMessage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contractPerson='" + contractPerson + '\'' +
                ", phone='" + phone + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                '}';
    }

    public boolean compare(AgencyMessage message){
        return this.toString().equals(message.toString());
    }
}
