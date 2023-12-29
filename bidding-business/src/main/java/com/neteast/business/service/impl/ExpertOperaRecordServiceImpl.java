package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.bid.CompletionStatus;
import com.neteast.business.domain.bid.res.ChooseConsistent;
import com.neteast.business.domain.bid.res.ScoreConsistent;
import com.neteast.business.domain.project.ExpertOperaRecord;
import com.neteast.business.mapper.ExpertOperaRecordMapper;
import com.neteast.business.service.IExpertOperaRecordService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lzp
 * @date 2023年12月28 18:22
 */

@Service
public class ExpertOperaRecordServiceImpl extends ServiceImpl<ExpertOperaRecordMapper, ExpertOperaRecord> implements IExpertOperaRecordService {

    @Resource
    ExpertOperaRecordMapper expertOperaRecordMapper;

    @Override
    public boolean updateRecordByItemId(ExpertOperaRecord record) {
        return this.lambdaUpdate().eq(ExpertOperaRecord::getItemId,record.getItemId()).
                eq(ExpertOperaRecord::getExpertId,record.getExpertId()).update(record);
    }

    /**
     * @Description 判断该供应商的该评分项是否符合一致性(分数)
     * @author lzp
     * @Date 2023/12/29
     */
    @Override
    public boolean getScoreConsistentBySupplierId(Integer itemId, Integer supplierId) {
        List<ScoreConsistent> list = expertOperaRecordMapper.getScoreConsistent(itemId,supplierId);
        Map<Integer,Long> map = list.stream().collect(Collectors.groupingBy(ScoreConsistent::getItemId, Collectors.counting()));
        return judgeConsistent(map);
    }

    /**
     * @Description 判断该供应商的该评分项是否符合一致性(选择)
     * @author lzp
     * @Date 2023/12/29
     */
    @Override
    public boolean getChooseConsistentBySupplierId(Integer itemId, Integer supplierId) {
        List<ChooseConsistent> list = expertOperaRecordMapper.getChooseConsistent(itemId,supplierId);
        Map<Integer,Long> map = list.stream().collect(Collectors.groupingBy(ChooseConsistent::getItemId,Collectors.counting()));
        return judgeConsistent(map);
    }

    /**
     * @Description 判断该供应商是否被淘汰
     * @author lzp
     * @Date 2023/12/29
     */
    @Override
    public boolean getOutBySupplierId(Integer supplierId,Integer itemId) {
        Long count = this.lambdaQuery().eq(ExpertOperaRecord::getSupplierId,supplierId)
                .eq(ExpertOperaRecord::getScoreItemId,itemId).eq(ExpertOperaRecord::getChoose,false).count();
        return count!=0;
    }

    /**
     * @Description 该专家该供应商的评分项完成情况
     * @author lzp
     * @Date 2023/12/29
     */
    @Override
    public Long getCountByCompletionStatus(CompletionStatus completionStatus) {
        return this.lambdaQuery().eq(ExpertOperaRecord::getSupplierId,completionStatus.getSupplierId())
                .eq(ExpertOperaRecord::getExpertId,completionStatus.getUserId())
                .eq(ExpertOperaRecord::getItemId,completionStatus.getItemId()).count();
    }

    /**
     * @Description 获取专家的操作记录某个评分项
     * @author lzp
     * @Date 2023/12/29
     */
    @Override
    public List<ExpertOperaRecord> getExpertOperaRecordByItemId(Integer supplierId, Integer expertId, Integer itemId) {
        return this.lambdaQuery().eq(ExpertOperaRecord::getSupplierId,supplierId)
                .eq(ExpertOperaRecord::getExpertId,expertId).eq(ExpertOperaRecord::getItemId,itemId).list();
    }

    private Boolean judgeConsistent(Map<Integer,Long> map){
        Collection<Long> res =  map.values();
        for (Long count:res) {
            if (count!=1){
                return false;
            }
        }
        return true;
    }
}
