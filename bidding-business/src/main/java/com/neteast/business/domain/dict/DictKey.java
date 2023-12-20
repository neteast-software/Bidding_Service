package com.neteast.business.domain.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 变量的Key
 * @author lzp
 * @date 2023年11月22 10:07
 */

@TableName("dict_key")
@Data
public class DictKey extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 主键名称 */
    @TableField("key")
    private String key;

    /** 是否通用 */
    @TableField("common")
    private Boolean common;

    /** key的值 */
    @TableField("label")
    private String label;

    /** Key的类型的id */
    @TableField("type_id")
    private Integer typeId;

    /** key的类型的值 */
    @TableField("type")
    private String type;

    /** 字典类型 1-单选 2-多选 3-填值等 */
    @TableField("dict_type")
    private Integer dictType;
}
