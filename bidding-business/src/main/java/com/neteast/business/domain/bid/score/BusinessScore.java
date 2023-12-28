package com.neteast.business.domain.bid.score;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 商务分
 * @author lzp
 * @date 2023年12月27 9:55
 */

@Data
public class BusinessScore {

    /** 商务分的总分 */
    private Double total;

    /** 该题目的做题情况 */
    private List<GradeItem> items = new ArrayList<>();

    public Double getValue(){
        Double score = 0.0D;
        for (GradeItem item :items) {
            score = score+item.getValue();
        }
        return score;
    }

    public int getNum(){
        return items.size();
    }

    public void setGradeItems(GradeItem gradeItem){
        for (GradeItem item:items) {
            if (item.getId().compareTo(gradeItem.getId())==0){
                item = gradeItem;
                return;
            }
        }
        items.add(gradeItem);
    }

}

