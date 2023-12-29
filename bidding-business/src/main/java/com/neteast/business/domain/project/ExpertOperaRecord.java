package com.neteast.business.domain.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neteast.business.domain.bid.score.GradeItem;
import com.neteast.business.domain.bid.score.PriceScore;
import com.neteast.business.domain.bid.score.RadioItem;
import com.neteast.business.domain.bid.score.SelectItem;
import lombok.Data;

/**
 * 专家操作记录(类似答案)
 * @author lzp
 * @date 2023年12月28 17:57
 */

@Data
@TableName("expert_opera_record")
public class ExpertOperaRecord {

    /** 主键id */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /** 供应商id */
    @TableField("supplier_id")
    private Integer supplierId;

    /** 专家id */
    @TableField("expert_id")
    private Integer expertId;

    /** 评分项id */
    @TableField("score_item_id")
    private Integer scoreItemId;

    /** 评分项类型 */
    @TableField("item_type")
    private String itemType;

    /** 评分项子项id */
    @TableField("item_id")
    private Integer itemId;

    /**
     * 选择题目类型(单选,选择加分)
     * 1- 一票否决 value值0.0 (客观题)
     * 2- 通过加该分 value有值 (客观题)
     * 3- 输入分值 (选择主观题/客观题)
     */
    @TableField("pass_type")
    private Integer passType;

    /** 分数题目类型
     * 1-主观题(同一个供应商，该子项得分不一致)
     * 2-客观题(同一个供应商，该子项得分一致)
     */
    @TableField("title_type")
    private Integer titleType;

    /** 选项展示
     * 1->单选 2->分数 3->分值
     */
    @TableField("input_type")
    private Integer inputType;

    /** 分数类型的值 */
    @TableField("value")
    private Double value;

    /** 单选类型的值 */
    @TableField("choose")
    private Boolean choose;

    public static GradeItem toGradItem(ExpertOperaRecord record){
        GradeItem gradeItem = new GradeItem();
        gradeItem.setId(record.getItemId());
        gradeItem.setValue(record.getValue());
        gradeItem.setInputType(record.getInputType());
        gradeItem.setTitleType(record.getTitleType());
        gradeItem.setInputType(record.getInputType());
        return gradeItem;
    }

    public static RadioItem toRadioItem(ExpertOperaRecord record){
        RadioItem radioItem = new RadioItem();
        radioItem.setId(record.getItemId());
        radioItem.setChoose(record.getChoose());
        radioItem.setInputType(record.getInputType());
        radioItem.setPassType(record.getPassType());
        radioItem.setTitleType(record.getTitleType());
        return radioItem;
    }

    public static SelectItem toSelectItem(ExpertOperaRecord record){
        SelectItem selectItem = new SelectItem();
        selectItem.setId(record.getItemId());
        selectItem.setChoose(record.getChoose());
        selectItem.setPassType(record.getPassType());
        selectItem.setInputType(record.getInputType());
        selectItem.setTitleType(record.getTitleType());
        selectItem.setValue(record.getValue());
        return selectItem;
    }

    public static PriceScore toPriceScore(ExpertOperaRecord record){
        PriceScore priceScore = new PriceScore();
        priceScore.setItemId(record.getItemId());
        priceScore.setItemType(record.getItemType());
        priceScore.setValue(record.getValue());
        return priceScore;
    }
}
