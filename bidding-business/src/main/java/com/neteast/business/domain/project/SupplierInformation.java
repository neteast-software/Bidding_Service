package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 招标项目供应商信息
 * @author lzp
 * @date 2023年12月11 9:56
 */

@Data
@TableName("supplier_information")
public class SupplierInformation extends BaseEntity{

    /** 供应商id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 分包id */
    @TableField("package_id")
    private Integer packageId;

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

    /** 供应商地址 */
    @TableField("address")
    private String address;

    /** 报名入账方式 */
    @TableField("pay_method")
    private String payMethod;

    /** 银行名称 */
    @TableField("bank_name")
    private String bankName;

    /** 银行地址 */
    @TableField("bank_address")
    private String bankAddress;

    /** 银行卡号 */
    @TableField("card_number")
    private String cardNumber;

    /** 银行卡账户名称 */
    @TableField("account_number")
    private String accountName;

    /** 报名时间 */
    @TableField("register_time")
    private Date registerTime;

    /** 是否中标 */
    @TableField("suc_bid")
    private boolean sucBid;

    /** 是否到达现场 */
    @TableField("in_scene")
    private boolean inScene;
}
