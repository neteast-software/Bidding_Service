package com.neteast.business.domain.template;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * (母)模板类型数据
 * @author lzp
 * @date 2023年11月17 13:37
 */

@Data
@TableName("template_type")
public class TemplateType extends BaseEntity {

    /** 主键 */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 模板的名称 */
    @TableField("name")
    private String name;

    /** 模板的使用次数 */
    @TableField("use_count")
    private Integer useCount;

    /** 模板类型 */
    @TableField("type")
    private String type;

    /** 是否公开 */
    @TableField("open")
    private String open;

    /** 模板招标类型 */
    @TableField("bidding_type")
    private String biddingType;

    /** 模板的项目类型 */
    @TableField("project_type")
    private String projectType;
}
