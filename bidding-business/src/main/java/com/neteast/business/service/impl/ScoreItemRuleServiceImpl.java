package com.neteast.business.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ScoreItemRule;
import com.neteast.business.domain.project.SpecialCondition;
import com.neteast.business.mapper.ScoreItemRuleMapper;
import com.neteast.business.service.IScoreItemRuleService;
import com.neteast.business.service.ISpecialConditionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lzp
 * @date 2024年01月02 10:44
 */

@Service
public class ScoreItemRuleServiceImpl extends ServiceImpl<ScoreItemRuleMapper, ScoreItemRule> implements IScoreItemRuleService {

    @Resource
    ISpecialConditionService specialConditionService;

    @Resource
    ScoreItemRuleMapper scoreItemRuleMapper;

    @Override
    public List<ScoreItemRule> getScoreItemList(ScoreItemRule scoreItemRule) {

        return scoreItemRuleMapper.getList(scoreItemRule);
    }

    @Override
    public ScoreItemRule getScoreItemRole(ScoreItemRule rule,Integer projectId,Integer packageId) {
        //获取相应规则
        List<ScoreItemRule> rules = this.lambdaQuery().eq(ScoreItemRule::getScoreMethod,rule.getScoreMethod())
                .eq(ScoreItemRule::getTradeMethod,rule.getTradeMethod())
                .eq(ScoreItemRule::getProjectType,rule.getProjectType())
                .eq(ScoreItemRule::getItemType,rule.getItemType())
                .list();

        //获取项目的特殊情况
        List<SpecialCondition> projectList = specialConditionService.lambdaQuery().eq(SpecialCondition::getType,1)
                .eq(SpecialCondition::getExtId,projectId).list();

        //获取分包的特殊情况
        List<SpecialCondition> packageList = specialConditionService.lambdaQuery().eq(SpecialCondition::getType,2)
                .eq(SpecialCondition::getExtId,packageId).list();

        projectList.addAll(packageList);

        Map<String,List<ScoreItemRule>> map = rules.stream().collect(Collectors.groupingBy(ScoreItemRule::getSpecialCondition));

        List<String> list = projectList.stream().map(SpecialCondition::getName).collect(Collectors.toList());

        ScoreItemRule scoreItemRule =  getItemTypeRule(map,list);
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
    private ScoreItemRule getItemTypeRule(Map<String,List<ScoreItemRule>> rules,List<String> list){

        if (list.size()==0){
            return null;
        }
        //符合的规则
        List<ScoreItemRule> use = new ArrayList<>();
        for (String rule:list) {
            List<ScoreItemRule> value = rules.get(rule);
            boolean res = judgeRule(value.get(0),rule);
            if (res){
                use.add(value.get(0));
            }
        }

        if (use.size()==0){
            return null;
        }else {
            ScoreItemRule scoreItemRule = new ScoreItemRule();
            ScoreItemRule max = use.stream().max(Comparator.comparing(ScoreItemRule::getMaxValue)).get();
            ScoreItemRule min = use.stream().min(Comparator.comparing(ScoreItemRule::getMinValue)).get();
            ScoreItemRule childMin = use.stream().min(Comparator.comparing(ScoreItemRule::getChildValue)).get();
            scoreItemRule.setMaxValue(max.getMaxValue());
            scoreItemRule.setMinValue(min.getMinValue());
            scoreItemRule.setChildValue(childMin.getChildValue());
            return scoreItemRule;
        }
    }

    /**
     * @Description 判断特殊情况
     * @author lzp
     * @Date 2024/1/2
     */
    private boolean judgeRule(ScoreItemRule rule,String itemName){

        //特殊情况
        Integer specialRelation = rule.getSpecialRelation();
        //如果无特殊情况下则直接包含
        if (specialRelation==null){
            return true;
        }
        //为1表示包含关系 为2表示排除
        if (specialRelation==1){
            return rule.getSpecialCondition().equals(itemName);
        }else{
            return !rule.getSpecialCondition().equals(itemName);
        }
    }
}
