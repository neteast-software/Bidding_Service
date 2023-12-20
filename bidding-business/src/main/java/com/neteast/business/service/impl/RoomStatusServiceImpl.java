package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.programme.RoomStatus;
import com.neteast.business.mapper.RoomStatusMapper;
import com.neteast.business.service.IRoomStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月20 15:37
 */

@Service
public class RoomStatusServiceImpl extends ServiceImpl<RoomStatusMapper, RoomStatus> implements IRoomStatusService {

    @Resource
    RoomStatusMapper roomStatusMapper;

    @Override
    public List<RoomStatus> getRoomStatusList(RoomStatus roomStatus) {
        return roomStatusMapper.getList(roomStatus);
    }

    @Override
    public List<RoomStatus> getRoomStatusListByTime(Date startTime, Date endTime) {

        Long start = startTime.getTime();
        Long end = endTime.getTime();
        return roomStatusMapper.getListByTime(start,end);
    }
}
