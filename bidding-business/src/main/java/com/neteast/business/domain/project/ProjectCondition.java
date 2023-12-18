package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目附加项内容
 * @author lzp
 * @date 2023年12月12 10:53
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("project_condition")
public class ProjectCondition {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 分包id */
    @TableField("package_id")
    private Integer packageId;

    /** 条件id */
    @TableField("condition_id")
    private Integer conditionId;

    @TableField(exist = false)
    private String name;
}
