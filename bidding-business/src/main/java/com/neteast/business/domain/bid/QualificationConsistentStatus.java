package com.neteast.business.domain.bid;

import com.neteast.business.domain.bid.score.RadioItem;
import lombok.Data;

import java.util.List;

/**
 * 资格审查的一致性
 * @author lzp
 * @date 2023年12月27 12:02
 */

@Data
public class QualificationConsistentStatus {

    /** 评分子项id */
    private Integer itemId;

    /** 评分项的值 */
    private List<RadioItem> values;

    /** 一致性情况 */
    private Boolean consistent = true;

    /** 一致性情况以及评分值更新 */
    public void setValue(RadioItem radioItem){
        boolean value = radioItem.getChoose();
        boolean flag = true;
        boolean exists = false;
        for (RadioItem item:values) {
            if (item.getChoose()!=value){
                flag = false;
            }

            if (radioItem.getId().compareTo(item.getId())!=0){
                item = radioItem;
                exists = true;
            }
        }
        if (!exists){
            values.add(radioItem);
        }
        consistent = flag;
    }
}
