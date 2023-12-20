package com.neteast.web.controller.programme;

import cn.hutool.core.date.DateUtil;
import com.neteast.business.domain.programme.MeetRoom;
import com.neteast.business.domain.programme.RoomStatus;
import com.neteast.business.domain.programme.vo.RoomStatusVO;
import com.neteast.business.domain.project.ProjectInformation;
import com.neteast.business.service.IMeetRoomService;
import com.neteast.business.service.IProjectInformationService;
import com.neteast.business.service.IRoomStatusService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.exception.BaseBusException;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSInput;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 会议室使用情况
 * @author lzp
 * @date 2023年12月20 15:34
 */

@RestController
@RequestMapping("/roomStatus")
public class RoomStatusController extends BaseController {

    @Resource
    IRoomStatusService roomStatusService;

    @Resource
    IProjectInformationService projectInformationService;

    @Resource
    IMeetRoomService meetRoomService;

    /**
     * @Description 返回当月的项目情况
     * @author lzp
     * @Date 2023/12/20
     */
    @GetMapping("/useMonth")
    public AjaxResult getRoomUseMonth(String date){

        List<RoomStatus> list = getToDayRoomStatusList(date,"yyyy-MM");
        Map<Integer,Long> map =  list.stream().collect(Collectors.groupingBy(RoomStatus::getDay,Collectors.counting()));
        return success(map);
    }

    /**
     * @Description 返回该月份下项目开标详情
     * @author lzp
     * @Date 2023/12/20
     */
    @GetMapping("/useMonthDetail")
    public AjaxResult getRoomUseMonthDetail(String date){

        List<RoomStatus> list = getToDayRoomStatusList(date,"yyyy-MM");
        Map<Integer,List<RoomStatus>> map = list.stream().collect(Collectors.groupingBy(RoomStatus::getDay));
        HashMap<Integer,List<RoomStatusVO>> result = new HashMap<>();
        map.forEach((k,v)->{
            List<RoomStatusVO> temp = new ArrayList<>();
            v.forEach(t->{
                RoomStatusVO roomStatusVO = RoomStatusVO.convert(t);
                temp.add(roomStatusVO);
            });
            result.put(k,temp);
        });
        return success(result);
    }

    /**
     * @Description 添加会议室使用
     * @author lzp
     * @Date 2023/12/20
     */
    @PostMapping("/add")
    public AjaxResult addRoomUse(@RequestBody RoomStatusVO roomStatusVO){

        List<RoomStatus> list = roomStatusService.getRoomStatusListByTime(roomStatusVO.getStartTime(),roomStatusVO.getEndTime());
        if (list.size()==0){
            RoomStatus roomStatus = RoomStatusVO.convert(roomStatusVO);
            if (roomStatus.getRoomId()==null){
                return error("未设置会议室");
            }
            if (roomStatus.getProjectId()==null){
                return error("未设置项目");
            }
            setRoomStatus(roomStatus);
            roomStatusService.save(roomStatus);
            return success();
        }
        return error("当前时间段已有会议室使用");
    }

    /**
     * @Description 更新
     * @author lzp
     * @Date 2023/12/20
     */
    @PostMapping("/update")
    public AjaxResult updateRoomUse(@RequestBody RoomStatusVO roomStatusVO){
        List<RoomStatus> list = roomStatusService.getRoomStatusListByTime(roomStatusVO.getStartTime(),roomStatusVO.getEndTime());
        if (list.size()==0){
            RoomStatus roomStatus = RoomStatusVO.convert(roomStatusVO);
            setRoomStatus(roomStatus);
            roomStatusService.updateById(roomStatus);
            return success();
        }
        return error("当前时间段已有会议室使用");
    }

    @PostMapping("/del/{id}")
    public AjaxResult delRoomUse(@PathVariable("id")Integer id){

        roomStatusService.removeById(id);
        return success();
    }

    private List<RoomStatus> getToDayRoomStatusList(String date,String pattern){

        Date time = DateUtil.parse(date,pattern);
        Integer year = DateUtil.year(time);
        Integer month = DateUtil.month(time);
        RoomStatus status = RoomStatus.builder().year(year).month(month).build();
        return roomStatusService.getRoomStatusList(status);
    }

    private void setRoomStatus(RoomStatus roomStatus) {

        Integer roomId = roomStatus.getRoomId();
        if (roomId!=null){
            MeetRoom meetRoom = meetRoomService.getById(roomId);
            if (meetRoom==null){
                throw new BaseBusException("不存在该文件");
            }
            roomStatus.setRoomName(meetRoom.getName());
            roomStatus.setRoomType(meetRoom.getType());
        }
        Integer projectId = roomStatus.getProjectId();
        if (projectId!=null){
            ProjectInformation information = projectInformationService.getById(projectId);
            if (information==null){
                throw new BaseBusException("不存在该项目");
            }
            roomStatus.setProjectName(information.getProjectName());
        }
    }


}
