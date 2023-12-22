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

    /** 评分项描述 */
    @TableField("content")
    private String content;

    /** 评分项的分值 */
    @TableField("value")
    private Double value;

    /** 选择内容值(json) */
    @TableField("choose")
    private String choose;
}
