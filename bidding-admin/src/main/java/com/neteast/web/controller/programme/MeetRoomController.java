package com.neteast.web.controller.programme;

import com.alibaba.fastjson2.JSONObject;
import com.corundumstudio.socketio.SocketIOServer;
import com.neteast.business.domain.programme.MeetRoom;
import com.neteast.business.service.IMeetRoomService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.web.controller.ws.SocketIOListener;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月20 11:07
 */

@RestController
@RequestMapping("/meetRoom")
public class MeetRoomController extends BaseController {

    @Resource
    IMeetRoomService meetRoomService;

    @Resource
    SocketIOServer socketIOServer;

    /**
     * @Description 初始化会议室通道号
     * @author lzp
     * @Date 2023/12/20
     */
    @PostConstruct
    public void initMeetRoomChannel(){

        List<MeetRoom> list = meetRoomService.getWholeMeetRoom();
        list.forEach(l->{
            logger.info("创建通道-{}",l.getChannelName());
            socketIOServer.addEventListener(l.getChannelName(), String.class, new SocketIOListener(socketIOServer));
        });
    }

    @GetMapping("/listByPage")
    public AjaxResult getMeetRoomListByPage(MeetRoom meetRoom){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<MeetRoom> list = meetRoomService.getMeetRoomList(meetRoom);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @PostMapping("/add")
    public AjaxResult addMeetRoom(@RequestBody MeetRoom meetRoom){

        List<MeetRoom> list = meetRoomService.lambdaQuery().eq(MeetRoom::getChannelName,meetRoom.getChannelName()).list();
        if (list!=null&&list.size()==0){
            meetRoomService.save(meetRoom);
            //创建新通道
            socketIOServer.addEventListener(meetRoom.getChannelName(),String.class,new SocketIOListener(socketIOServer));
            return addSuccess();
        }
        return error("该通道号已存在");
    }

    @PostMapping("/update")
    public AjaxResult updateMeetRoom(@RequestBody MeetRoom meetRoom){

        MeetRoom temp = meetRoomService.getById(meetRoom.getId());
        if (!temp.getChannelName().equals(meetRoom.getChannelName())){
            socketIOServer.addEventListener(meetRoom.getChannelName(),String.class,new SocketIOListener(socketIOServer));
        }
        meetRoomService.updateById(meetRoom);
        return updateSuccess();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delMeetRoom(@PathVariable("id")Integer id){

        meetRoomService.removeById(id);
        return delSuccess();
    }
}
