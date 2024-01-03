package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评分项规则
 *
 * @author lzp
 * @date 2023年12月29 16:31
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("score_item_rule")
public class ScoreItemRule extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 评分方式 */
    @TableField("score_method")
    private Integer scoreMethod;

    /** 项目交易方式 */
    @TableField("trade_method")
    private Integer tradeMethod;

    /** 项目类型 */
    @TableField("project_type")
    private Integer projectType;

    /** 特殊条件的关系类型 1-包含 2-排除 */
    @TableField("special_relation")
    private Integer specialRelation;

    /** 特殊条件 */
    @TableField("special_condition")
    private String specialCondition;

    /** 评分项类型 */
    @TableField("item_type")
    private String itemType;

    /** 最大分 */
    @TableField("max_value")
    private Double maxValue;

    /** 最低分 */
    @TableField("min_value")
    private Double minValue;

    /** 评分子项最大分 */
    @TableField("child_value")
    private Double childValue;
}
