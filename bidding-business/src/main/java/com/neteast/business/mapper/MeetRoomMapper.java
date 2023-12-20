package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.programme.MeetRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月20 11:08
 */

@Mapper
public interface MeetRoomMapper extends BaseMapper<MeetRoom> {

    List<MeetRoom> getList(MeetRoom meetRoom);
}
