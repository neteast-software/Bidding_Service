package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @author lzp
 * @date 2023年11月17 14:42
 */

@Data
public class ProjectSupplier extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 供应商id */
    @TableField("supplier_id")
    private Integer supplierId;

    /** 供应商名称 */
    @TableField("name")
    private String name;

    /** 供应商地址 */
    @TableField("address")
    private String address;

    /** 中标金额 */
    @TableField("money")
    private Double money;
}
