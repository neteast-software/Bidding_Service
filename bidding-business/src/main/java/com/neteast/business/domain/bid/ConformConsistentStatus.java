package com.neteast.business.domain.bid;

import com.neteast.business.domain.bid.score.ConformScore;
import com.neteast.business.domain.bid.score.SelectItem;
import lombok.Data;

import java.util.List;

/**
 * 符合性的一致性
 * @author lzp
 * @date 2023年12月27 15:06
 */

@Data
public class ConformConsistentStatus {

    /** 评分子项id */
    private Integer itemId;

    /** 评分项的值 */
    private List<SelectItem> values;

    /** 一致性情况 */
    private Boolean consistent = true;

    public void setValue(SelectItem selectItem){

        boolean value = selectItem.getChoose();
        boolean flag = true;
        boolean exists = false;

        for (SelectItem item:values) {
            if (item.getChoose()!=value){
                flag = false;
            }

            if (item.getId().compareTo(selectItem.getId())==0){
                item = selectItem;
                exists = true;
            }
        }
        if (!exists){
            values.add(selectItem);
        }
        consistent = flag;
    }
}
