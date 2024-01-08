package com.neteast.business.domain.project.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.neteast.business.domain.project.ProjectScoreItem;
import com.neteast.business.domain.project.ScoreItem;
import lombok.Data;

import java.util.ArrayList;

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
     * 2- 偏离扣分 (客观题)
     * 3- 输入分值 (选择主观题/客观题)
     */
    private Integer passType;

    /** 分数题目类型
     * 1-主观题(同一个供应商，该子项得分不一致)
     * 2-客观题(同一个供应商，该子项得分一致)
     */
    private Integer judgeType;

    /** 评分项描述 */
    private String content;

    /** 评分项取值的最大值 */
    private Double max = 0.0D;

    /** 评分项取值的最小值 */
    private Double min = 0.0D;

    /** 选择内容 */
    private ArrayList<Double> selectArray = new ArrayList<>();

    public static ScoreItemVO convert(ScoreItem scoreItem){
        ScoreItemVO scoreItemVO = new ScoreItemVO();
        BeanUtil.copyProperties(scoreItem,scoreItemVO);
        return scoreItemVO;
    }

    /** 商务分 */
    public static ScoreItem toBusiness(ScoreItemVO vo){
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setContent(vo.getContent());
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setPassType(3);
        scoreItem.setMax(vo.getMax());
        scoreItem.setMin(vo.getMin());
        scoreItem.setExtId(vo.getExtId());
        scoreItem.setJudgeType(vo.getJudgeType());
        scoreItem.setInputType(vo.getInputType());
        if (vo.getInputType()==2){
            scoreItem.setMax(vo.getMax());
            scoreItem.setMin(vo.getMin());
        }else if (vo.getInputType()==3){
            scoreItem.setMax(vo.getMax());
            StringBuilder content = new StringBuilder();
            for (Double score: vo.getSelectArray()) {
                content.append(score.toString()).append(",");
            }
            scoreItem.setSelectArray(content.toString());
        }
        return scoreItem;
    }

    /** 技术分 */
    public static ScoreItem toTech(ScoreItemVO vo){
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setContent(vo.getContent());
        scoreItem.setPassType(vo.getPassType());
        scoreItem.setInputType(vo.getInputType());
        if (vo.getInputType()==2){
            scoreItem.setMax(vo.getMax());
            scoreItem.setMin(vo.getMin());
        }else if (vo.getInputType()==3){
            scoreItem.setMax(vo.getMax());
            StringBuilder content = new StringBuilder();
            for (Double score: vo.getSelectArray()) {
                content.append(score.toString()).append(",");
            }
            scoreItem.setSelectArray(content.toString());
        }else if (vo.getInputType()==1){
            scoreItem.setMax(vo.getMax());
            scoreItem.setMin(vo.getMin());
        }
        scoreItem.setExtId(vo.getExtId());
        scoreItem.setJudgeType(vo.getJudgeType());
        return scoreItem;
    }

    /** 符合性 */
    public static ScoreItem toConform(ScoreItemVO vo){
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setContent(vo.getContent());
        Integer passType = vo.getPassType();
        scoreItem.setPassType(passType);
        if (passType==2){
            scoreItem.setMin(vo.getMin());
            scoreItem.setMax(vo.getMax());
        }
        scoreItem.setJudgeType(2);
        scoreItem.setExtId(vo.getExtId());
        scoreItem.setInputType(1);
        return scoreItem;
    }

    /** 价格分 */
    public static ScoreItem toPrice(ScoreItemVO vo){
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setContent(vo.getContent());
        scoreItem.setTitle(vo.getTitle());
        scoreItem.setExtId(vo.getExtId());
        scoreItem.setPassType(3);
        scoreItem.setMax(vo.getMax());
        scoreItem.setMin(vo.getMin());
        scoreItem.setJudgeType(2);
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
        scoreItem.setJudgeType(2);
        scoreItem.setMax(vo.getMax());
        scoreItem.setMin(0.0D);
        return scoreItem;
    }
}
