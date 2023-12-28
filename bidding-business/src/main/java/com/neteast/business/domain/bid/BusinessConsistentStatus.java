package com.neteast.business.domain.bid;

import com.neteast.business.domain.bid.score.GradeItem;
import lombok.Data;

import java.util.List;

/**
 * 商务的一致性
 * @author lzp
 * @date 2023年12月27 11:38
 */

@Data
public class BusinessConsistentStatus {

    /** 评分子项id */
    private Integer itemId;

    /** 评分项的值 */
    private List<GradeItem> values;

    /** 一致性情况 */
    private Boolean consistent = true;

    /**
     * @Description 添加记录值以及判断一致性
     * @author lzp
     * @Date 2023/12/27
     */
    public void setValue(GradeItem gradeItem){

        boolean flag = true;
        boolean exists = false;
        Double consistentValue = gradeItem.getValue();
        for (GradeItem item:values) {
            if (item.getValue().compareTo(consistentValue)!=0){
                flag = false;
            }
            if (item.getId().compareTo(gradeItem.getId())==0){
                item = gradeItem;
                exists = true;
            }
        }

        if (!exists){
            values.add(gradeItem);
        }
        consistent = flag;
    }
}
