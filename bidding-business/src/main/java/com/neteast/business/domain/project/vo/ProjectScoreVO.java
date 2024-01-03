package com.neteast.business.domain.project.vo;

import cn.hutool.core.bean.BeanUtil;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItemRule;
import lombok.Data;

/**
 * 评分项详情
 * @author lzp
 * @date 2024年01月02 11:25
 */

@Data
public class ProjectScoreVO {

    /** 评分项id */
    private Integer id;

    /** 项目id */
    private Integer projectId;

    /** 分包id */
    private Integer packageId;

    /** 评分项名称 */
    private String itemName;

    /** 评分项类型 */
    private String itemType;

    /** 该项总分值 默认0.0 */
    private Double value;

    /** 子项内容汇总 */
    private String content;

    /** 子项数量 */
    private Integer num;

    /** 排序 */
    private Integer sort;

    /** 该项的最大分值 */
    private Double maxValue;

    /** 该项的最小分值 */
    private Double minValue;

    /** 评分子项最大分 */
    private Double childValue;

    public static ProjectScoreVO convert(ProjectScoreItem item, ScoreItemRule rule){
        ProjectScoreVO vo = new ProjectScoreVO();
        BeanUtil.copyProperties(item,vo);
        vo.setMaxValue(rule.getMaxValue());
        vo.setMinValue(rule.getMinValue());
        vo.setChildValue(rule.getChildValue());
        return vo;
    }
}
