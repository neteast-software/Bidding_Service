package com.neteast.business.domain.bid;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商评审的总情况
 * @author lzp
 * @date 2023年12月13 9:38
 */

@Data
public class TotalScore {

    /** 类型 1-选择 2-分值 */
    private Integer type;

    /** 评分项类型 */
    private String itemType;

    /**专家完成情况*/
    private List<CompletionStatus> completionStatuses = new ArrayList<>();

    private Integer num;

    public void setCompletionStatus(CompletionStatus completionStatus){

        for (CompletionStatus c:completionStatuses){
            if (completionStatus.getUserId().compareTo(c.getUserId())==0){
                c.setName(completionStatus.getName());
                num = num - c.getNum() + completionStatus.getNum();
                c.setNum(completionStatus.getNum());
                if (type==1){
                    c.setPass(completionStatus.getPass());
                }else {
                    c.setValue(completionStatus.getValue());
                }
                return;
            }
        }
        completionStatuses.add(completionStatus);
        num = num + completionStatus.getNum();
    }
}
