package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.business.domain.template.ItemTemplate;
import lombok.Data;

import java.util.List;

/**
 * 评分方式(与评分项关联)
 * 评分方式的计算方式
 * @author lzp
 * @date 2023年12月21 17:51
 */

@Data
@TableName("score_method")

public class ScoreMethod {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 评分方式名称 */
    @TableField("score_name")
    private String scoreName;

    @TableField(exist = false)
    private List<ItemTemplate> itemTemplates;

}
