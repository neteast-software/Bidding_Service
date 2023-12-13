package com.neteast.business.domain.project;

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
    private Integer projectId;

    /** 条件id */
    private Integer conditionId;

}
