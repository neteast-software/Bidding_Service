package com.neteast.business.domain.bid;

import com.neteast.business.domain.bid.score.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * 供应商评审时状态
 * @author lzp
 * @date 2023年12月08 17:10
 */

@Data
public class SupplierBidMsg {

    /** 供应商id */
    private Integer supplierId;

    /** 供应商名称 */
    private String supplierName;

    /** 项目id */
    private Integer packageId;

    /** 供应商的淘汰情况 */
    private List<SelectItem> selectItems = new ArrayList<>();

    private List<RadioItem> radioItems = new ArrayList<>();

    /** SelectItem的淘汰情况 */
    private Boolean selectOutStatus = false;

    /** RadioItem的淘汰情况 */
    private Boolean radioOutStatus = false;

   /** 商务分一致性 */
   HashMap<Integer,BusinessConsistentStatus> businessConsistentStatus = new HashMap<>();

   /** 符合性一致性 */
   HashMap<Integer,ConformConsistentStatus> conformConsistentStatus = new HashMap<>();

   /** 技术一致性 */
   HashMap<Integer,TechConsistentStatus> techConsistentStatuses = new HashMap<>();

   /** 资格审查一致性 */
   HashMap<Integer,QualificationConsistentStatus> qualificationConsistentStatuses = new HashMap<>();

   /** 供应商专家做题情况 把题目类型作为key */
   private HashMap<String,ReviewStatus> reviewStatuses = new HashMap<>();

   /** 价格分情况 */
   private List<SupplierPrice> priceStatus = new ArrayList<>();

   private Boolean priceStatusConsistent = true;

   public boolean getOutStatus(){
       return selectOutStatus||radioOutStatus;
   }

   public void addReviewStatus(CompletionStatus completionStatus,String itemType){
        ReviewStatus status = this.reviewStatuses.get(itemType);
        if (status==null){
            ReviewStatus reviewStatus = new ReviewStatus();
            reviewStatus.setCompletionStatus(completionStatus);
            reviewStatuses.put(itemType,reviewStatus);
        }else {
            status.setCompletionStatus(completionStatus);
        }
   }

   /** 是否被淘汰情况(SelectItem) */
   public void addOutNumStatus(SelectItem selectItem){

       boolean exists = false;
       for (SelectItem item:selectItems) {
           if (selectItem.getId().compareTo(selectItem.getId())==0){
               item = selectItem;
               exists = true;
           }
       }
       if (!exists){
           selectItems.add(selectItem);
       }
       for (SelectItem item:selectItems) {
           if (!item.getChoose()){
               selectOutStatus = true;
               break;
           }
       }
   }

    /** 是否被淘汰情况(RadioItem) */
   public void addOutNumStatus(RadioItem radioItem){

       boolean exists = false;
       for (RadioItem item:radioItems) {
           if (radioItem.getId().compareTo(radioItem.getId())==0){
               item = radioItem;
               exists = true;
           }
       }
       if (!exists){
           radioItems.add(radioItem);
       }
       for (RadioItem item:radioItems) {
           if (!item.getChoose()){
               radioOutStatus = true;
               break;
           }
       }
   }

   /** 价格分添加 */
   public void addPriceStatus(SupplierPrice supplierPrice){

       boolean flag = true;
       boolean exists = false;
       Double value = supplierPrice.getValue();
       for (SupplierPrice price:priceStatus) {
           if (price.getValue().compareTo(value)!=0){
               flag = false;
           }
           if (price.getUserId().equals(supplierPrice.getUserId())){
               price = supplierPrice;
               exists = true;
           }
       }
       if (!exists){
           priceStatus.add(supplierPrice);
       }
       priceStatusConsistent = flag;
   }

   /** 添加商务一致性 */
   public void addBusinessConsistent(GradeItem gradeItem){
       BusinessConsistentStatus status = businessConsistentStatus.get(gradeItem.getId());
       if (status==null){
           BusinessConsistentStatus consistentStatus = new BusinessConsistentStatus();
           consistentStatus.setValue(gradeItem);
           businessConsistentStatus.put(gradeItem.getId(),consistentStatus);
       }else {
           status.setValue(gradeItem);
       }
   }

    /** 获取商务一致性 */
   public boolean getBusinessConsistent(){
       Collection<BusinessConsistentStatus> list = businessConsistentStatus.values();
       for (BusinessConsistentStatus status:list) {
           if (!status.getConsistent()){
               return false;
           }
       }
       return true;
   }

   /** 符合性一致性 */
   public void addConformConsistent(SelectItem selectItem){
       ConformConsistentStatus status = conformConsistentStatus.get(selectItem.getId());
       if (status == null){
           ConformConsistentStatus consistentStatus = new ConformConsistentStatus();
           consistentStatus.setValue(selectItem);
           conformConsistentStatus.put(selectItem.getId(),consistentStatus);
       }else {
           status.setValue(selectItem);
       }
   }

    /** 获取符合性一致性 */
    public boolean getConformConsistent(){
        Collection<ConformConsistentStatus> list = conformConsistentStatus.values();
        for (ConformConsistentStatus status:list) {
            if (!status.getConsistent()){
                return false;
            }
        }
        return true;
    }

   /** 技术一致性 */
   public void addTechConsistent(GradeItem gradeItem){
       TechConsistentStatus status = techConsistentStatuses.get(gradeItem.getId());
       if (status!=null){
           TechConsistentStatus consistentStatus = new TechConsistentStatus();
           consistentStatus.setValue(gradeItem);
           techConsistentStatuses.put(gradeItem.getId(),consistentStatus);
       }else {
           status.setValue(gradeItem);
       }
   }

    /** 获取技术一致性 */
    public boolean getTechConsistent(){
        Collection<TechConsistentStatus> list = techConsistentStatuses.values();
        for (TechConsistentStatus status:list) {
            if (!status.getConsistent()){
                return false;
            }
        }
        return true;
    }

   /** 资格审查一致性 */
   public void addQualificationConsistent(RadioItem radioItem){
       QualificationConsistentStatus status = qualificationConsistentStatuses.get(radioItem.getId());
       if (status==null){
           QualificationConsistentStatus consistentStatus = new QualificationConsistentStatus();
           consistentStatus.setValue(radioItem);
           qualificationConsistentStatuses.put(radioItem.getId(),consistentStatus);
       }else {
           status.setValue(radioItem);
       }
   }

    /** 获取资格审查一致性 */
    public boolean getQualificationConsistent(){
        Collection<QualificationConsistentStatus> list = qualificationConsistentStatuses.values();
        for (QualificationConsistentStatus status:list) {
            if (!status.getConsistent()){
                return false;
            }
        }
        return true;
    }

}
