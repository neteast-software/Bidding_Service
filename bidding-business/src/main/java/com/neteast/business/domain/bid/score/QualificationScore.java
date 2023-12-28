package com.neteast.business.domain.bid.score;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 资格审查
 * @author lzp
 * @date 2023年12月27 9:56
 */

@Data
public class QualificationScore{

    private List<RadioItem> items = new ArrayList<>();

    public int getNum(){
        return items.size();
    }

    public void setRadioItem(RadioItem radioItem){
        for (RadioItem item:items) {
            if (item.getId().compareTo(radioItem.getId())==0){
                item = radioItem;
                return;
            }
        }
        items.add(radioItem);
    }
}
