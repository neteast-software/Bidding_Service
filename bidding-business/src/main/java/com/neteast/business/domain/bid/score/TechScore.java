package com.neteast.business.domain.bid.score;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 技术分
 * @author lzp
 * @date 2023年12月27 10:10
 */

@Data
public class TechScore {

    /** 总分 */
    private Double total;

    /** 子项分数 */
    private List<GradeItem> items = new ArrayList<>();

    public Double getValue(){
        Double score = 0.0D;
        for (GradeItem item:items) {
            score = score + item.getValue();
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
