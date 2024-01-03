package com.neteast.business.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ScoreItemRule;
import com.neteast.business.mapper.ScoreItemRuleMapper;
import com.neteast.business.service.IScoreItemRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzp
 * @date 2024年01月02 10:44
 */

@Service
public class ScoreItemRuleServiceImpl extends ServiceImpl<ScoreItemRuleMapper, ScoreItemRule> implements IScoreItemRuleService {

    @Override
    public ScoreItemRule getScoreItemRole(ScoreItemRule rule) {
        //获取相应规则
        List<ScoreItemRule> rules = this.lambdaQuery().eq(ScoreItemRule::getScoreMethod,rule.getScoreMethod())
                .eq(ScoreItemRule::getTradeMethod,rule.getTradeMethod())
                .eq(ScoreItemRule::getProjectType,rule.getProjectType())
                .eq(ScoreItemRule::getItemType,rule.getItemType())
                .list();

        ScoreItemRule scoreItemRule =  getItemTypeRule(rules,rule.getSpecialCondition());
        if (scoreItemRule==null){
            rule.setMaxValue(100.0);
            rule.setMinValue(0.0);
            rule.setChildValue(100.0);
            return rule;
        }
        return scoreItemRule;
    }

    /**
     * @Description 判断该评分项的规则
     * @author lzp
     * @Date 2024/1/2
     */
    private ScoreItemRule getItemTypeRule(List<ScoreItemRule> rules,String itemName){

        for (ScoreItemRule rule:rules) {
            boolean res = judgeRule(rule,itemName);
            if (res){
                return rule;
            }
        }
        return null;
    }

    /**
     * @Description 判断特殊情况
     * @author lzp
     * @Date 2024/1/2
     */
    private boolean judgeRule(ScoreItemRule rule,String itemName){

        //特殊情况
        int specialRelation = rule.getSpecialRelation();
        //为1表示包含关系 为2表示排除
        if (specialRelation==1){
            return rule.getSpecialCondition().equals(itemName);
        }else{
            return !rule.getSpecialCondition().equals(itemName);
        }
    }
}
