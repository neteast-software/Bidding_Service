package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 项目状态管理
 * @author lzp
 * @date 2023年12月21 17:31
 */

@Data
@TableName("project_status")
public class ProjectStatus {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 阶段id */
    @TableField("stage_id")
    private Integer stageId;

    /** 阶段序号 */
    @TableField("step_num")
    private Integer stepNum;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 阶段时间 */
    @TableField("stage_time")
    private Date stageTime;

    /** 该阶段文件数量 */
    @TableField("num")
    private Integer num;

    public void changeNum(Integer change){
        this.num = this.num + change;
    }
}
