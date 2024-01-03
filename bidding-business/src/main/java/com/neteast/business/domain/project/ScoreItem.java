package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 项目评分项的子项
 * @author lzp
 * @date 2023年12月12 11:44
 */

@Data
@TableName("score_item")
public class ScoreItem {

    /** 选项id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 子项标题 */
    @TableField("title")
    private String title;

    /** 项目的评分项id */
    @TableField("ext_id")
    private Integer extId;

    /** 选项展示
     * 1->单选 2->分数 3->分值
     */
    @TableField("input_type")
    private Integer inputType;

    /**
     * 选择题目类型(单选,选择加分)
     * 1- 一票否决 value值0.0 (客观题)
     * 2- 通过加该分 value有值 (客观题)
     * 3- 输入分值 (选择主观题/客观题)
     */
    @TableField("pass_type")
    private Integer passType;

    /** 分数题目类型
     * 1-主观题(同一个供应商，该子项得分不一致)
     * 2-客观题(同一个供应商，该子项得分一致)
     */
    @TableField("judge_type")
    private Integer judgeType;

    /** 评分项描述 */
    @TableField("content")
    private String content;

    /** 评分项的分值,无则为0.0 */
    @TableField("value")
    private Double value = 0.0D;
}
