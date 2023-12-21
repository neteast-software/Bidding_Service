package com.neteast.business.domain.editor.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.neteast.business.domain.editor.ProjectBidding;
import lombok.Data;

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

    /** 阶段名称 */
    private String stageName;

    /** 文件名称 */
    private String fileName;

    public static ProjectBidding convert(ProjectBiddingVO vo){
        ProjectBidding bidding = new ProjectBidding();
        bidding.setId(vo.getId());
        bidding.setProjectId(vo.getProjectId());
        bidding.setStageId(vo.getStageId());
        bidding.setFileName(vo.getFileName());
        bidding.setStageName(vo.getStageName());
        return bidding;
    }
}
