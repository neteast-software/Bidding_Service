package com.neteast.business.domain.project.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItem;
import lombok.Data;

/**
 * 评分项子项设置
 * @author lzp
 * @date 2023年12月27 17:42
 */

@Data
public class ScoreItemVO {

    /** 评分子项名称 */
    private String title;

    /** 评分项id */
    private Integer extId;

    /** 评分项类型 */
    private String itemType;

    /** 选项展示
     * 1->单选 2->分数 3->分值
     */
    private Integer inputType;

    /**
     * 选择题目类型(单选,选择加分)
     * 1- 一票否决 value值0.0 (客观题)
     * 2- 通过加该分 value有值 (客观题)
     * 3- 输入分值 (选择主观题/客观题)
     */
    private Integer passType;

    /** 分数题目类型
     * 1-主观题(同一个供应商，该子项得分不一致)
     * 2-客观题(同一个供应商，该子项得分一致)
     */
    private Integer titleType;

    /** 评分项描述 */
    private String content;

    /** 评分项的分值,无则为0.0 */
    private Double value = 0.0D;

    /** 商务分 */
    public static ScoreItem toBusiness(ScoreItemVO vo){
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setContent(vo.getContent());
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setPassType(3);
        scoreItem.setValue(vo.getValue());
        scoreItem.setExtId(vo.getExtId());
        scoreItem.setTitleType(vo.getTitleType());
        scoreItem.setInputType(vo.getInputType());
        return scoreItem;
    }

    /** 技术分 */
    public static ScoreItem toTech(ScoreItemVO vo){
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setContent(vo.getContent());
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setPassType(3);
        scoreItem.setValue(vo.getValue());
        scoreItem.setExtId(vo.getExtId());
        scoreItem.setTitleType(vo.getTitleType());
        scoreItem.setInputType(vo.getInputType());
        return scoreItem;
    }

    /** 符合性 */
    public static ScoreItem toConform(ScoreItemVO vo){
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setContent(vo.getContent());
        Integer passType = vo.getPassType();
        scoreItem.setPassType(passType);
        if (passType==1){
            scoreItem.setValue(0.0D);
        }else {
            //扣除分数
            scoreItem.setValue(vo.getValue());
        }
        scoreItem.setTitleType(2);
        scoreItem.setExtId(vo.getExtId());
        scoreItem.setInputType(vo.getInputType());
        return scoreItem;
    }

    /** 价格分 */
    public static ScoreItem toPrice(ScoreItemVO vo){
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setContent(vo.getContent());
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setExtId(vo.getExtId());
        scoreItem.setPassType(3);
        scoreItem.setValue(vo.getValue());
        scoreItem.setTitleType(2);
        scoreItem.setInputType(2);
        return scoreItem;
    }

    /** 资格审查 */
    public static ScoreItem toQualification(ScoreItemVO vo){
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setContent(vo.getContent());
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setExtId(vo.getExtId());
        scoreItem.setPassType(1);
        scoreItem.setInputType(1);
        scoreItem.setTitleType(2);
        scoreItem.setValue(0.0D);
        return scoreItem;
    }
}
