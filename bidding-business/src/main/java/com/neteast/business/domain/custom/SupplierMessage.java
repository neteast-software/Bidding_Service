package com.neteast.business.domain.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 供应商信息
 * @author lzp
 * @date 2023年11月17 17:07
 */

@Data
@TableName("supplier_message")
public class SupplierMessage extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    /** 供应商名称 */
    @TableField("name")
    private String name ;

    /** 地址 */
    @TableField("address")
    private String address ;

}
