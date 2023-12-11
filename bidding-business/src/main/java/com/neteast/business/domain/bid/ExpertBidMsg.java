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
    private List<Technical> technicals = new ArrayList<>();

    /** 商务评审 */
    private List<Commercial> commercials = new ArrayList<>();

    /** 资格评审 */
    private List<Qualification> qualifications = new ArrayList<>();

    /** 价格评审 */
    private List<Price> prices = new ArrayList<>();

    public void setTechnicals(Technical technical) {
        for (Technical temp : technicals) {
            if (temp.getId().equals(technical.getId())){
                temp.setValue(technical.isValue());
                return;
            }
        }
        technicals.add(technical);
    }

    public void setCommercials(Commercial commercial){
        for (Commercial temp:commercials) {
            if (temp.getId().equals(commercial.getId())){
                temp.setValue(commercial.isValue());
                return;
            }
        }
        commercials.add(commercial);
    }

    public void setQualifications(Qualification qualification){
        for (Qualification temp:qualifications){
            if (temp.getId().equals(qualification.getId())){
                temp.setValue(qualification.isValue());
                return;
            }
        }
        qualifications.add(qualification);
    }

    public void setPrices(Price price){
        for (Price temp:prices){
            if (temp.getId().equals(price.getId())){
                temp.setValue(price.getValue());
                return;
            }
        }
        prices.add(price);
    }
}
