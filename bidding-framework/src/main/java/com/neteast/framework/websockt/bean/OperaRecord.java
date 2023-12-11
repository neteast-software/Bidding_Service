package com.neteast.framework.websockt.bean;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;

/**
 * 评标操作记录
 * @author lzp
 * @date 2023年12月08 14:38
 */

@Data
public class OperaRecord {

    /** 记录id */
    private int id;

    /** 用户id */
    private int userId;

    /** 用户名称 */
    private String userName;

    /** 接收角色 */
    private String receiver;

    /** 用户操作记录 */
    private String record;

    /** 会议记录通道 */
    private String channel;

    /** 操作时间 */
    private Date time;
}
