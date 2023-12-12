package com.neteast.business.domain.bid;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家评标记录
 * @author lzp
 * @date 2023年12月08 18:19
 */

@Data
public class ExpertBidMsg {

    /** 专家id */
    private Integer id;

    /** 专家名称 */
    private String name;

    /** 供应商id */
    private Integer supplierId;

    /** 分包id */
    private Integer packageId;

    /** 技术评审 */
    private List<Score> technicals = new ArrayList<>();

    /** 商务评审 */
    private List<Score> commercials = new ArrayList<>();

    /** 资格评审 */
    private List<Score> qualifications = new ArrayList<>();

    /** 价格评审 */
    private List<Score> prices = new ArrayList<>();

    public void setTechnicals(Score score) {
        for (Score temp : technicals) {
            if (setScore(temp,score)){
                return;
            }
        }
        technicals.add(score);
    }

    public void setCommercials(Score score){
        for (Score temp:commercials) {
            if (setScore(temp,score)){
                return;
            }
        }
        commercials.add(score);
    }

    public void setQualifications(Score score){
        for (Score temp:qualifications){
            if (setScore(temp,score)){
                return;
            }
        }
        qualifications.add(score);
    }

    public void setPrices(Score score){
        for (Score temp:prices){
            if (setScore(temp,score)){
                return;
            }
        }
        prices.add(score);
    }

    private boolean setScore(Score temp,Score score){
        if (temp.getId().equals(score.getId())){
            temp.setType(score.getType());
            if (score.getType()==1){
                temp.setChoose(score.getChoose());
            }else {
                temp.setValue(score.getValue());
            }
            return true;
        }
        return false;
    }
}
