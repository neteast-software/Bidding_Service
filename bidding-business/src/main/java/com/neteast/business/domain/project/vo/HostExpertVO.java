package com.neteast.business.domain.project.vo;

import lombok.Data;

/**
 * 主持人端的专家信息数据
 * @author lzp
 * @date 2023年12月28 16:09
 */

@Data
public class HostExpertVO {

    /** 专家id */
    private Integer expertId;

    /** 专家名称 */
    private String expertName;

    public static HostExpertVO convert(ProjectExpertVO expert){
        HostExpertVO vo = new HostExpertVO();
        vo.setExpertId(vo.getExpertId());
        vo.setExpertName(expert.getName());
        return vo;
    }
}
