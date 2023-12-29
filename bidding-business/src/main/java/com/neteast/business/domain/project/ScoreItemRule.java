package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 评分项规则
 * 以匹配上为判断条件，匹配上则以该条件基准
 * 优先级有默认关系兜底
 * 在匹配中有排除和包含关系：使用ScoreRule进行表示
 * @author lzp
 * @date 2023年12月29 16:31
 */

@Data
@TableName("score_item_rule")
public class ScoreItemRule {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 评分方式 */
    @TableField("score_method")
    private String scoreMethod;

    /** 项目交易方式 */
    @TableField("trade_method")
    private String tradeMethod;

    /** 项目类型 */
    @TableField("project_type")
    private String projectType;

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

    /** 优先级 */
    @TableField("order")
    private Integer order;

}
