package com.neteast.business.domain.custom.vo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.neteast.business.domain.custom.BankMessage;
import com.neteast.business.domain.custom.ContractMessage;
import com.neteast.business.domain.custom.SupplierMessage;
import lombok.Data;

import java.util.List;

/**
 * @author lzp
 * @date 2023年11月17 17:34
 */

@Data
public class SupplierMessageVO {

    /** 主键id */
    private Integer id ;

    /** 供应商名称 */
    private String name ;

    /** 地址 */
    private String address ;

    private List<BankMessage> bankMessages;

    private List<ContractMessage> contractMessages;

    public static SupplierMessageVO convert(SupplierMessage supplierMessage){
        SupplierMessageVO supplierMessageVO = new SupplierMessageVO();
        BeanUtil.copyProperties(supplierMessage,supplierMessageVO);
        return supplierMessageVO;
    }
}
