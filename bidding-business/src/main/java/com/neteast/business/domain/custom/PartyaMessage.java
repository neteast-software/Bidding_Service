package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 甲方信息
 * @author lzp
 * @date 2023年11月14 10:51
 */

@Data
@TableName("project_message")
public class PartyaMessage extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 公司名称 */
    @TableField("name")
    private String name;

    /** 公司类型 */
    @TableField("type")
    private String type;

    /** 公司所在地 */
    @TableField("address")
    private String address;

    @TableField("postcode")
    private String postcode;

    /** 公司法人 */
    @TableField("legal_person")
    private String legalPerson;

}