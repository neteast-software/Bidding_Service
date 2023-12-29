package com.neteast.business.domain.bid.score;

import lombok.Data;

/**
 * 分数/分值题
 * @author lzp
 * @date 2023年12月27 10:13
 */

@Data
public class GradeItem {

    /** 评分项子项id */
    private Integer id;

    /** 分数题目类型
     * 1-主观题(同一个供应商，该子项得分不一致)
     * 2-客观题(同一个供应商，该子项得分一致)
     */
    private Integer titleType;

    /**
     * 选择题目类型(单选,选择加分)
     * 1- 一票否决 value值0.0 (客观题)
     * 2- 通过加该分 value有值 (客观题)
     * 3- 输入分值 (选择主观题/客观题)
     */
    private Integer passType;

    /** 1->单选 2->分数 3->分值 */
    private Integer inputType;

    /** 取值 */
    private Double value;
}
