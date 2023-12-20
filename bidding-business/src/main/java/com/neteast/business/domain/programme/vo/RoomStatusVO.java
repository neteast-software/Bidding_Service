package com.neteast.business.domain.programme.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neteast.business.domain.programme.RoomStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author lzp
 * @date 2023年12月20 16:07
 */

@Data
public class RoomStatusVO {

    /** 主键id */
    private Integer id;

    /** 项目id */
    private Integer projectId;

    /** 项目名称 */
    private String projectName;

    /** 会议室id */
    private Integer roomId;

    /** 会议室名称 */
    private String roomName;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 会议室类型 (1-开标室 2-评标室) */
    private Integer roomType;

    public static RoomStatusVO convert(RoomStatus roomStatus){
        RoomStatusVO roomStatusVO = new RoomStatusVO();
        BeanUtil.copyProperties(roomStatus,roomStatusVO);
        return roomStatusVO;
    }

    public static RoomStatus convert(RoomStatusVO roomStatusVO){
        RoomStatus roomStatus = new RoomStatus();
        BeanUtil.copyProperties(roomStatusVO,roomStatus);
        Date time = roomStatusVO.getStartTime();
        Integer year = DateUtil.year(time);
        Integer month = DateUtil.month(time);
        Integer day = DateUtil.dayOfMonth(time);
        roomStatus.setYear(year);
        roomStatus.setMonth(month);
        roomStatus.setDay(day);
        return roomStatus;
    }

}
