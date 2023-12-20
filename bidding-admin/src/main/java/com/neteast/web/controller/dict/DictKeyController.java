package com.neteast.web.controller.dict;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.dict.DictKey;
import com.neteast.business.domain.dict.DictType;
import com.neteast.business.domain.dict.DictValue;
import com.neteast.business.domain.dict.vo.DictKeyVO;
import com.neteast.business.service.IDictKeyService;
import com.neteast.business.service.IDictTypeService;
import com.neteast.business.service.IDictValueService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import com.neteast.common.exception.BaseBusException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 变量的key
 * @author lzp
 * @date 2023年11月22 10:59
 */

@RestController
@RequestMapping("/dictKey")
public class DictKeyController extends BaseController {

    @Resource
    IDictKeyService dictKeyService;

    @Resource
    IDictTypeService dictTypeService;

    @Resource
    IDictValueService dictValueService;

    @GetMapping("/listByPage")
    public AjaxResult getDictKeyListByPage(DictKeyVO dictKeyVO){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<DictKeyVO> list = dictKeyService.getDictKeyList(dictKeyVO);
        list.forEach(l->{
            List<DictValue> values = dictValueService.getKeyValue(l.getId());
            l.setValues(values);
        });
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/list")
    public AjaxResult getDictKeyList(DictKeyVO dictKeyVO){

        List<DictKeyVO> list = dictKeyService.getDictKeyList(dictKeyVO);
        list.forEach(l->{
            List<DictValue> values = dictValueService.getKeyValue(l.getId());
            l.setValues(values);
        });
        return success(list);
    }

    @GetMapping("/one")
    public AjaxResult getDictKeyOne(DictKeyVO dictKeyVO){

        DictKey dictKey = dictKeyService.getById(dictKeyVO.getId());
        List<DictValue> values = dictValueService.getKeyValue(dictKey.getId());
        DictKeyVO temp = DictKeyVO.convert(dictKey);
        temp.setValues(values);
        return success(temp);
    }

    @PostMapping("/update")
    public AjaxResult updateDictKeyData(@RequestBody DictKeyVO dictKeyVO){

        DictKey dictKey = DictKeyVO.convert(dictKeyVO);
        DictType dictType = dictTypeService.getById(dictKey.getTypeId());
        if (dictType!=null){
            dictKey.setType(dictType.getType());
        }
        dictKeyService.updateById(dictKey);
        return success();
    }

    @PostMapping("/add")
    public AjaxResult addDictKeyData(@RequestBody DictKeyVO dictKeyVO){

        DictKey dictKey = DictKeyVO.convert(dictKeyVO);
        DictType dictType = dictTypeService.getById(dictKey.getTypeId());
        if (dictType!=null){
            dictKey.setType(dictType.getType());
            dictKeyService.save(dictKey);
            return success();
        }
        return error("该字典类型不存在");
    }

    @PostMapping("/del/{id}")
    public AjaxResult delDictKeyData(@PathVariable Integer id){

        dictKeyService.delDictKey(id);
        return success();
    }
}
