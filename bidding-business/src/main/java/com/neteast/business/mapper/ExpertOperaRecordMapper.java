package com.neteast.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neteast.business.domain.bid.res.ChooseConsistent;
import com.neteast.business.domain.bid.res.ScoreConsistent;
import com.neteast.business.domain.project.ExpertOperaRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lzp
 * @date 2023年12月28 18:20
 */

@Mapper
public interface ExpertOperaRecordMapper extends BaseMapper<ExpertOperaRecord> {

    /**
     * @Description 判断分数的一致性
     * @author lzp
     * @Date 2023/12/29
     */
    List<ScoreConsistent> getScoreConsistent(@Param("scoreItemId")Integer itemId,@Param("supplierId")Integer supplierId);

    /**
     * @Description 判断选择的一致性
     * @author lzp
     * @Date 2023/12/29
     */
    List<ChooseConsistent> getChooseConsistent(@Param("scoreItemId")Integer itemId,@Param("supplierId")Integer supplierId);
}
