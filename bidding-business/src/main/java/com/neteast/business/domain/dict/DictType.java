package com.neteast.business.domain.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 字典类型
 * @author lzp
 * @date 2023年12月19 18:02
 */

@Data
@TableName("dict_type")
public class DictType {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 主键名称 */
    @TableField("type")
    private String type;
}
