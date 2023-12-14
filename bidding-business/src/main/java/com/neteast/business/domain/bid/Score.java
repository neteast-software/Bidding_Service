package com.neteast.business.domain.bid;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 得分
 * @author lzp
 * @date 2023年12月12 13:36
 */

@Data
public class Score {

    /** 评审的类型 */
    private String itemType;

    /** 类型 1-选择 2-分值 */
    private Integer type;

    /** 评审项选择情况 */
    private List<Item> list = new ArrayList<>();

    public void setList(Item item){
        for (Item temp:list) {
            if (temp.getId().compareTo(item.getId())==0){
                if (type==1){
                    temp.setChoose(item.getChoose());
                }else {
                    temp.setValue(item.getValue());
                }
                return;
            }
        }
        list.add(item);
    }

    /** 评审项的得分情况 */
    public Double getValue(){

        double total = 0.0D;
        for (Item item:list){
            total = total+item.getValue();
        }
        return total;
    }

    /** 一致性判断 */
    public Boolean getPass(){

        if (type==1){
            return null;
        }
        for (Item item:list) {
            if (!item.getChoose()){
                return false;
            }
        }
        return true;
    }
}
