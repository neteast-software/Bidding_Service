package com.neteast.business.domain.project.vo;

import lombok.Data;

/**
 * 专家提交状态
 * @author lzp
 * @date 2023年12月28 14:32
 */

@Data
public class ExpertSubmitVO {

    /** 项目id */
    private Integer projectId;

    /** 分包id */
    private Integer packageId;

    /** 用户id */
    private Integer userId;

    /** 阶段id */
    private Integer stepId;

    /** 阶段名称 */
    private String stepName;

    /** 通道号 */
    private String channel;
}
