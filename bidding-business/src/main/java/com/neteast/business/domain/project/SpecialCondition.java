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
@TableName("special_condition")
public class SpecialCondition {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** (项目/分包)id */
    @TableField("ext_id")
    private Integer extId;

    /** 特殊情况名称 */
    @TableField("name")
    private String Name;

    /** 1-项目类型 2-分包类型 */
    @TableField("type")
    private Integer type;
}
