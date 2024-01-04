package com.neteast.business.domain.project.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.neteast.common.annotation.DictData;
import lombok.Data;

/**
 * @author lzp
 * @date 2024年01月04 10:07
 */

@Data
public class ScoreItemRuleVO {

    /** 主键id */
    private Integer id;

    /** 评分方式 */
    @DictData(dictType = "score_method",valueField = "scoreMethod")
    private String scoreMethod;

    /** 项目交易方式 */
    @DictData(dictType = "project_trade_type",valueField = "tradeMethod")
    private String tradeMethod;

    /** 项目类型 */
    private String projectType;

    /** 比较关系类型 */
    @DictData(dictType = "compare_relation",valueField = "specialRelation")
    private String specialRelation;

    /** 特殊条件 */
    @DictData(dictType = "project_special_condition",valueField = "specialCondition")
    private String specialCondition;

    /** 评分项类型 */
    @DictData(dictType = "score_item_type",valueField = "itemType")
    private String itemType;

    /** 最大分 */
    private Double maxValue;

    /** 最低分 */
    private Double minValue;

    /** 评分子项最大分 */
    private Double childValue;

}
