package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 项目特殊情况 字典上做为参照
 * @author lzp
 * @date 2024年01月03 11:35
 */

@Data
@TableName("rule_condition")
public class RuleCondition {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** (项目/包id) */
    @TableField("ext_id")
    private Integer extId;

    /** 特殊规则id */
    @TableField("rule_id")
    private Integer ruleId;

    @TableField("rule_belong")
    private Integer ruleBelong;
}
