package com.neteast.business.domain.project.vo;

import com.neteast.business.domain.project.ProjectScoreItem;
import lombok.Data;

/**
 * 主持人端的题目展示
 * @author lzp
 * @date 2023年12月28 16:12
 */

@Data
public class HostTitleVO {

    /** 评分项id */
    private Integer id;

    /** 评分项名称 */
    private String itemName;

    /** 评分项类型 */
    private String itemType;

    /** 评分项的题目数量 */
    private Integer num;

    public static HostTitleVO convert(ProjectScoreItem item){
        HostTitleVO vo = new HostTitleVO();
        vo.setId(item.getId());
        vo.setItemName(item.getItemName());
        vo.setItemType(item.getItemType());
        vo.setNum(item.getNum());
        return vo;
    }
}
