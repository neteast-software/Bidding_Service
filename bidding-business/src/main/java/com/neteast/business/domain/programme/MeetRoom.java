package com.neteast.business.domain.programme;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 会议室
 * @author lzp
 * @date 2023年12月20 10:47
 */

@Data
@TableName("meet_room")
public class MeetRoom extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 会议室名称 */
    @TableField("name")
    private String name;

    /** 会议室通道号 */
    @TableField("channel_name")
    private String channelName;

    /** 会议室类型 1-开标室 2-评标室 */
    @TableField("type")
    private Integer type;
}
