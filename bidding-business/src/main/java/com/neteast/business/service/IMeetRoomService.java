package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.programme.MeetRoom;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月20 11:08
 */
public interface IMeetRoomService extends IService<MeetRoom> {

    List<MeetRoom> getMeetRoomList(MeetRoom meetRoom);

    List<MeetRoom> getWholeMeetRoom();
}
