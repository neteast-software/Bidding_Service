package com.neteast.business.domain.bid.score;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 符合性
 * @author lzp
 * @date 2023年12月27 9:54
 */

@Data
public class ConformScore {

    /** 总分 */
    private Double total;

    private List<SelectItem> items = new ArrayList<>();

    /** 获取未通过数 */
    public int getNoPassNum(){
        int num = 0;
        for (SelectItem item:items) {
            if (item.getPassType()==2&&!item.getChoose()){
                num++;
            }
        }
        return num;
    }

    public int getNum(){
        return items.size();
    }

    /** 获取剩余分 */
    public Double getValue(){
        Double score = 0.0D;
        for (SelectItem item:items) {
            if (item.getPassType()==2&&!item.getChoose()){
                score = score + item.getValue();
            }
        }
        return total - score;
    }

    public void setSelectItem(SelectItem selectItem){
        for (SelectItem item:items) {
            if (item.getId().compareTo(selectItem.getId())==0){
                item = selectItem;
                return;
            }
        }
        items.add(selectItem);
    }
}
