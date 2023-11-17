package com.neteast.business.domain.custom.vo;

import cn.hutool.core.bean.BeanUtil;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.PartyaMessage;
import lombok.Data;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 17:34
 */

@Data
public class PartyaMessageVO {

    private Integer id;

    /** 公司名称 */
    private String name;

    /** 公司类型 */
    private String type;

    /** 公司所在地 */
    private String address;

    /** 邮编 */
    private String postcode;

    /** 公司法人 */
    private String legalPerson;

    private List<BankMessage> bankMessages;

    private List<ContractMessage> contractMessages;

    public static PartyaMessageVO convert(PartyaMessage partyaMessage){
        PartyaMessageVO partyaMessageVO = new PartyaMessageVO();
        BeanUtil.copyProperties(partyaMessage,partyaMessageVO);
        return partyaMessageVO;
    }
}
