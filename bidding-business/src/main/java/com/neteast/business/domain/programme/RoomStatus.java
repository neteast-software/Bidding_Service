package com.neteast.business.domain.programme;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neteast.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 会议室使用情况
 * @author lzp
 * @date 2023年12月20 15:16
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("room_status")
public class RoomStatus extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 项目id */
    @TableField("project_id")
    private Integer projectId;

    /** 项目名称 */
    @TableField("project_name")
    private String projectName;

    /** 会议室id */
    @TableField("room_id")
    private Integer roomId;

    /** 会议室名称 */
    @TableField("room_name")
    private String roomName;

    /** 开始时间 */
    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @TableField("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 年份 */
    @TableField("year")
    private Integer year;

    /** 月份 */
    @TableField("month")
    private Integer month;

    /** 天 */
    @TableField("day")
    private Integer day;

    /** 会议室类型 (1-开标室 2-评标室) */
    @TableField("room_type")
    private Integer roomType;
}
