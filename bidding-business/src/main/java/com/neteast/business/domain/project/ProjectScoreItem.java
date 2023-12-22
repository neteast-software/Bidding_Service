package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * 项目的评分项
 * @author lzp
 * @date 2023年12月12 11:26
 */

@Data
@TableName("project_score_item")
public class ProjectScoreItem {

    /** 评分项id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 分包id */
    @TableField("package_id")
    private Integer packageId;

    /** 评分项名称 */
    @TableField("item_name")
    private String itemName;

    /** 评分项类型 */
    @TableField("item_type")
    private String itemType;

    /** 值类型(1单选,2分数) */
    @TableField("value_type")
    private Integer valueType;

    /** 该项总分值 */
    @TableField("value")
    private Double value;

    /** 子项内容汇总 */
    @TableField("content")
    private String content;

    /** 子项数量 */
    @TableField("num")
    private Integer num;

    /** 该评分的评分项*/
    @TableField(exist = false)
    private List<ScoreItem> scoreItems;
}
