package com.neteast.business.domain.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 变量的历史记录
 * @author lzp
 * @date 2023年11月22 10:07
 */

@TableName("dict_history")
public class DictHistory{

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** key的id */
    @TableField("key_id")
    private Integer keyId;

    /** 值的id */
    @TableField("value_id")
    private Integer valueId;

    /** 历史值 */
    @TableField("history_value")
    private String historyValue;

    /** 历史时间 */
    @TableField("history_time")
    private Date historyTime;

    /** 操作人 */
    @TableField("do_by")
    private String doBy;
}
