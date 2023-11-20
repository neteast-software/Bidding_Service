package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 甲方供应商信息
 * @author lzp
 * @date 2023年11月17 11:41
 */

@Data
@TableName("project_supplier_message")
public class ProjectSupplierMessage extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 分包id */
    @TableField("package_id")
    private Integer packageId;

    /** 是否推荐 **/
    @TableField("recommend")
    private String recommend;

    /** 供应商名称 */
    @TableField("name")
    private String name;

    /** 入账方式 */
    @TableField("entry_method")
    private String entryMethod;

    /** 报名时间 */
    @TableField("register_time")
    private Date registerTime;

    /** 保证金情况 */
    @TableField("earnest_money")
    private String earnestMoney;
}
