package com.neteast.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neteast.business.domain.dict.PlusesCondition;
import com.neteast.business.domain.dict.vo.PlusesConditionVO;
import com.neteast.business.mapper.PlusesConditionMapper;
import com.neteast.business.service.IPlusesConditionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月15 10:46
 */

@Service
public class PlusesConditionServiceImpl extends ServiceImpl<PlusesConditionMapper, PlusesCondition> implements IPlusesConditionService {

    @Resource
    PlusesConditionMapper plusesConditionMapper;

    @Override
    public List<PlusesConditionVO> getPlusesConditionList(PlusesConditionVO plusesConditionVO) {
        return plusesConditionMapper.getList(plusesConditionVO);
    }

    @Override
    public boolean delPlusesCondition(Integer id) {

        PlusesCondition plusesCondition = getById(id);
        plusesCondition.setDel(0);
        updateById(plusesCondition);
        return true;
    }
}
