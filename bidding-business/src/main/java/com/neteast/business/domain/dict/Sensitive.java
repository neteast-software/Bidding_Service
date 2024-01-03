package com.neteast.business.domain.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 敏感词 不同的行业不同的敏感词
 * @author lzp
 * @date 2023年12月11 10:31
 */

@Data
@TableName("sensitive")
public class Sensitive extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 是否通用 */
    @TableField("common")
    private Integer common;

    /** 项目交易类型 */
    @TableField("trade_type")
    private Integer tradeType;

    /** 项目行业类型 */
    @TableField("project_industry")
    private Integer projectIndustry;

    /** 敏感词 */
    @TableField("word")
    private String word;
}