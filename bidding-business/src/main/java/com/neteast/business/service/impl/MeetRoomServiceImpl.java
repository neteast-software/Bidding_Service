package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.programme.MeetRoom;
import com.neteast.business.mapper.MeetRoomMapper;
import com.neteast.business.service.IMeetRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月20 11:09
 */

@Service
public class MeetRoomServiceImpl extends ServiceImpl<MeetRoomMapper, MeetRoom> implements IMeetRoomService {

    @Resource
    MeetRoomMapper meetRoomMapper;

    @Override
    public List<MeetRoom> getMeetRoomList(MeetRoom meetRoom) {
        return meetRoomMapper.getList(meetRoom);
    }

    @Override
    public List<MeetRoom> getWholeMeetRoom() {
        return this.lambdaQuery().list();
    }
}
