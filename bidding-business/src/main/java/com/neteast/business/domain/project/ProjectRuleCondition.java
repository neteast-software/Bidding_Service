package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 项目规则条件选择
 * @author lzp
 * @date 2024年01月04 10:49
 */

@Data
@TableName("project_rule_condition")
public class ProjectRuleCondition {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 条件归属 */
    @TableField("condition_belong")
    private Integer conditionBelong;

    /** 条件名称 */
    @TableField("condition_name")
    private String conditionName;

    /** 是否删除 */
    @TableField("del")
    private Boolean del;
}
