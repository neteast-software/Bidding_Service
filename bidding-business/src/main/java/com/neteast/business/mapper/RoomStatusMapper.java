package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.programme.RoomStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月20 15:35
 */

@Mapper
public interface RoomStatusMapper extends BaseMapper<RoomStatus> {

    List<RoomStatus> getList(RoomStatus roomStatus);

    List<RoomStatus> getListByTime(@Param("startTime") Long startTime, @Param("endTime")Long endTime);
}
