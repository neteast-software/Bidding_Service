package com.neteast.business.domain.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 变量值
 * @author lzp
 * @date 2023年11月22 10:07
 */

@TableName("dict_value")
@Data
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

    /** 键名 */
    @TableField("value_name")
    private String valueName;

    /** 是否启用 */
    @TableField("use")
    private Integer use;

}
