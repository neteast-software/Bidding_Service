package com.neteast.business.domain.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 敏感词
 * @author lzp
 * @date 2023年12月11 10:31
 */

@Data
@TableName("sensitive")
public class Sensitive {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 敏感词 */
    @TableField("word")
    private String word;

    /** 创建时间 */
    @TableField("create_time")
    private Date createTime;
}