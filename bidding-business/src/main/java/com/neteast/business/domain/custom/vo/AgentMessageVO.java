package com.neteast.business.domain.custom.vo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.neteast.business.domain.custom.AgentMessage;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import lombok.Data;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 17:33
 */

@Data
public class AgentMessageVO {

    private Long id;

    private String companyName;

    private String companyAddress;

    private String legalPerson;

    private String companyNet;

    private String postcode;

    private List<BankMessage> bankMessages;

    private List<ContractMessage> contractMessages;

    public static AgentMessageVO convert(AgentMessage agentMessage){
        AgentMessageVO agentMessageVO = new AgentMessageVO();
        BeanUtil.copyProperties(agentMessage,agentMessageVO);
        return agentMessageVO;
    }
}
