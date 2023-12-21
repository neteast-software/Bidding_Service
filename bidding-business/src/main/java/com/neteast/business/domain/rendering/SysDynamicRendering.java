package com.neteast.business.domain.rendering;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 渲染表单
 * @author lzp
 * @date 2023年12月21 10:19
 */

@Data
@TableName("sys_dynamic_rendering")
public class SysDynamicRendering extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 模板名称 */
    @TableField("module")
    private String module;

    /** 页面名称 */
    @TableField("page")
    private String page;

    /** 方法名称 */
    @TableField("method")
    private String method;

    /** 渲染内容 */
    @TableField("rendering")
    private String rendering;
}
