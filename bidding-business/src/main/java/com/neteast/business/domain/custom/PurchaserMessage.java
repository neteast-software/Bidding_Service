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

    @TableField("purchaser_name")
    private String purchaserName;

    @TableField("purchaser_address")
    private String purchaserAddress;

    @TableField("purchaser_phone")
    private String purchaserPhone;

    @TableField("purchaser_person")
    private String purchaserPerson;

    @Override
    public String toString() {
        return "PurchaserMessage{" +
                "id=" + id +
                ", purchaserName='" + purchaserName + '\'' +
                ", purchaserAddress='" + purchaserAddress + '\'' +
                ", purchaserPhone='" + purchaserPhone + '\'' +
                ", purchaserPerson='" + purchaserPerson + '\'' +
                '}';
    }

    public boolean compare(PurchaserMessage message){
        return this.toString().equals(message.toString());
    }
}
