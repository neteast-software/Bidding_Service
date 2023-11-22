package com.neteast.business.domain.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;

/**
 * 变量值
 * @author lzp
 * @date 2023年11月22 10:07
 */

@TableName("dict_value")
public class DictValue extends BaseEntity{

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** key的id */
    @TableField("key_id")
    private Integer keyId;

    /** 键值 */
    @TableField("value")
    private String value;
}
