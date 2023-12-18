package com.neteast.business.domain.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 项目附加项,条件等 如环保加分等
 * @author lzp
 * @date 2023年12月12 10:40
 */

@Data
@TableName("pluses_condition")
public class PlusesCondition extends BaseEntity{

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 附件项名称 */
    @TableField("name")
    private String name;

    /** 是否删除附加项 0-删除 1-不删除 */
    @TableField("del")
    private Integer del;
}
