package com.neteast.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 甲方信息
 * @author lzp
 * @date 2023年11月14 10:51
 */

@Data
@TableName("partya_message")
public class PartyaMessage {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    /** 公司名称 */
    @TableField("company_name")
    private String companyName ;

    /** 公司所在地 */
    @TableField("company_address")
    private String companyAddress ;

    /** 公司联系方式 */
    @TableField("company_contact")
    private String companyContact ;

    /** 公司类型 */
    @TableField("company_type")
    private String companyType ;

    /** 公司法人 */
    @TableField("legal_person")
    private String legalPerson ;
}
