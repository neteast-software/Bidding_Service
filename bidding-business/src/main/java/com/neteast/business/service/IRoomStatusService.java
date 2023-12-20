package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.programme.RoomStatus;

import java.util.Date;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月20 15:36
 */
public interface IRoomStatusService extends IService<RoomStatus> {

    List<RoomStatus> getRoomStatusList(RoomStatus roomStatus);

    List<RoomStatus> getRoomStatusListByTime(Date startTime,Date endTime);
}
