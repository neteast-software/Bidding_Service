package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 项目附加项内容
 * @author lzp
 * @date 2023年12月12 10:53
 */

@Data
@TableName("project_plus_condition")
public class ProjectPlusCondition {

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 条件id */
    @TableField("condition_id")
    private Integer conditionId;
}
