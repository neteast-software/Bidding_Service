package com.neteast.business.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.project.ScoreItemRule;
import com.neteast.business.domain.project.RuleCondition;
import com.neteast.business.domain.project.vo.ScoreItemRuleVO;
import com.neteast.business.mapper.ScoreItemRuleMapper;
import com.neteast.business.service.IScoreItemRuleService;
import com.neteast.business.service.IRuleConditionService;
import com.neteast.common.annotation.DictDataClass;
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
    IRuleConditionService specialConditionService;

    @Resource
    ScoreItemRuleMapper scoreItemRuleMapper;

    @Override
    public List<ScoreItemRule> getScoreItemList(ScoreItemRule scoreItemRule) {

        return scoreItemRuleMapper.getList(scoreItemRule);
    }

    @Override
    @DictDataClass
    public List<ScoreItemRuleVO> getScoreItemListByDict(ScoreItemRule scoreItemRule) {

        return scoreItemRuleMapper.getListByDict(scoreItemRule);
    }

    @Override
    public ScoreItemRule getScoreItemRole(ScoreItemRule rule,Integer projectId,Integer packageId) {
        //获取相应规则
        List<ScoreItemRule> rules = this.lambdaQuery().eq(rule.getScoreMethod()!=null,ScoreItemRule::getScoreMethod,rule.getScoreMethod())
                .eq(rule.getTradeMethod()!=null,ScoreItemRule::getTradeMethod,rule.getTradeMethod())
                .eq(rule.getProjectType()!=null,ScoreItemRule::getProjectType,rule.getProjectType())
                .eq(StrUtil.isNotBlank(rule.getItemType()),ScoreItemRule::getItemType,rule.getItemType())
                .list();

        //获取项目的特殊情况
        List<RuleCondition> projectList = specialConditionService.lambdaQuery().eq(RuleCondition::getRuleBelong,1)
                .eq(RuleCondition::getExtId,projectId).list();

        //获取分包的特殊情况
        List<RuleCondition> packageList = specialConditionService.lambdaQuery().eq(RuleCondition::getRuleBelong,2)
                .eq(RuleCondition::getExtId,packageId).list();

        projectList.addAll(packageList);

        Map<Integer,List<ScoreItemRule>> map = rules.stream().collect(Collectors.groupingBy(ScoreItemRule::getSpecialCondition));

        List<Integer> list = projectList.stream().map(RuleCondition::getRuleId).collect(Collectors.toList());

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
    private ScoreItemRule getItemTypeRule(Map<Integer,List<ScoreItemRule>> rules,List<Integer> list){

        if (list.size()==0){
            return null;
        }
        //符合的规则
        List<ScoreItemRule> use = new ArrayList<>();
        for (Integer rule:list) {
            List<ScoreItemRule> value = rules.get(rule);
            boolean res = judgeRule(value.get(0));
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
    private boolean judgeRule(ScoreItemRule rule){

        //特殊情况
        Integer specialRelation = rule.getSpecialRelation();
        //如果无特殊情况下则直接包含
        if (specialRelation==null){
            return true;
        }
        //为1表示包含关系 为2表示排除
        return specialRelation == 1;
    }
}
