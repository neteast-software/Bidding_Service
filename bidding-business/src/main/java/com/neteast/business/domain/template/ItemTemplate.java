package com.neteast.business.domain.template;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 评分项模板
 * @author lzp
 * @date 2023年12月15 10:05
 */

@Data
@TableName("item_template")
public class ItemTemplate extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 评分项名称 */
    @TableField("item_name")
    private String itemName;

    /** 评分项类型(价格性,商务性等) */
    @TableField("item_type")
    private String itemType;

    /** 值类型(1单选,2分数) */
    @TableField("value_type")
    private Integer valueType;

    /** 该项的总分值 */
    @TableField("value")
    private Double value;
}
