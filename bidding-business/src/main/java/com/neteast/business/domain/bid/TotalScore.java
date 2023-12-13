package com.neteast.business.domain.bid;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商总得分计算
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

    public void setCompletionStatus(CompletionStatus completionStatus){

        for (CompletionStatus c:completionStatuses){
            if (completionStatus.getUserId().compareTo(c.getUserId())==0){
                c.setName(completionStatus.getName());
                c.setNum(completionStatus.getNum());
                if (type==1){
                    c.setPass(completionStatus.isPass());
                }else {
                    c.setValue(completionStatus.getValue());
                }
                return;
            }
        }
        completionStatuses.add(completionStatus);
    }
}
