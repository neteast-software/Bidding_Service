package com.neteast.business.domain.template;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.business.domain.project.ProjectScoreItem;
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

    /** 评分方式id */
    @TableField("score_id")
    private Integer scoreId;

    /** 评分项名称 */
    @TableField("item_name")
    private String itemName;

    /** 评分项类型(价格性,商务性等) */
    @TableField("item_type")
    private String itemType;

    /** 排序字段 */
    @TableField("sort")
    private Integer sort;

    public static ProjectScoreItem covert(ItemTemplate itemTemplate){
        ProjectScoreItem scoreItem = new ProjectScoreItem();
        scoreItem.setItemName(itemTemplate.getItemName());
        scoreItem.setSort(itemTemplate.getSort());
        scoreItem.setItemType(itemTemplate.getItemType());
        return scoreItem;
    }
}
