package com.neteast.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.vo.ContractMessageVO;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 13:53
 */
public interface IContractMessageService extends IService<ContractMessage> {

    /**
     * @Description 获取对应对象的所有联系方式
     * @author lzp
     * @Date 2023/11/17
     */
    List<ContractMessage> getContractMessageByExId(ContractMessage contractMessage);

    /**
     * @Description 获取联系方式信息
     * @author lzp
     * @Date 2023/11/17
     */
    List<ContractMessageVO> getContractMessageList(ContractMessageVO contractMessageVO);

}
