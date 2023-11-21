package com.neteast.business.domain.project;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.business.domain.project.vo.ProjectInformationVO;
import com.neteast.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 流标记录标
 * @author lzp
 * @date 2023年11月14 10:52
 */

@Data
@TableName("fail_bidding_message")
public class FailBiddingMsg extends BaseEntity {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    /** 项目id */
    @TableField("ext_project_id")
    private Integer extProjectId ;

    /** 流标原因 **/
    @TableField("fail_reason")
    private String failReason;

    public static FailBiddingMsg convert(ProjectInformationVO projectInformationVO){
        FailBiddingMsg failBiddingMsg = new FailBiddingMsg();
        failBiddingMsg.setExtProjectId(projectInformationVO.getId());
        failBiddingMsg.setFailReason(projectInformationVO.getFailReason());
        return failBiddingMsg;
    }
}
