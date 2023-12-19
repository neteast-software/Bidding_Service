package com.neteast.business.domain.template;

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
 * 评分项子项模板
 * @author lzp
 * @date 2023年12月14 10:44
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("score_template")
public class ScoreTemplate extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 评分项模板id */
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
