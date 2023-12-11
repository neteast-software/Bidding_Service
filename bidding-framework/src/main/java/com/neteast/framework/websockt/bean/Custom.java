package com.neteast.framework.websockt.bean;

import lombok.Data;

import java.util.UUID;

/**
 * 客户端用户
 * @author lzp
 * @date 2023年11月29 17:43
 */

@Data
public class Custom {

    /** 用户角色 */
    String role;

    /** 用户id */
    String userId;

    /** 通道 */
    String channel;

    /** client的Id */
    UUID uuid;
}
