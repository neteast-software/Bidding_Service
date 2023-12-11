package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 招标项目供应商信息
 * @author lzp
 * @date 2023年12月11 9:56
 */

@Data
@TableName("supplier_information")
public class SupplierInformation {

    /** 供应商id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 供应商名称 */
    @TableField("name")
    private String name;

    /** 联系人 */
    @TableField("contract_person")
    private String contractPerson;

    /** 联系电话 */
    @TableField("phone")
    private String phone;

    /** 联系邮箱 */
    @TableField("email")
    private String email;

    /** 报名入账方式 */
    @TableField("pay_method")
    private String payMethod;

    /** 报名时间 */
    @TableField("register_time")
    private Date registerTime;

    /** 是否中标 */
    @TableField("suc_bid")
    private boolean sucBid;
}
