package com.neteast.business.domain.editor.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neteast.business.domain.editor.ProjectBidding;
import lombok.Data;

import java.util.Date;

/**
 * 项目标书文件
 * @author lzp
 * @date 2023年12月15 17:43
 */

@Data
public class ProjectBiddingVO {

    /** 主键id */
    private Integer id;

    /** 项目id */
    private Integer projectId;

    /** 阶段id */
    private Integer stageId;

    /** 阶段序号 */
    private Integer stageNum;

    /** 阶段名称 */
    private String stageName;

    /** 文件名称 */
    private String fileName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public static ProjectBidding convert(ProjectBiddingVO vo){
        ProjectBidding bidding = new ProjectBidding();
        bidding.setId(vo.getId());
        bidding.setProjectId(vo.getProjectId());
        bidding.setStageId(vo.getStageId());
        bidding.setFileName(vo.getFileName());
        bidding.setStageName(vo.getStageName());
        bidding.setStageNum(vo.getStageNum());
        return bidding;
    }
}
