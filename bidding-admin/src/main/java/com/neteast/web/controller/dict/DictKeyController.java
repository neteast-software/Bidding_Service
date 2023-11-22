package com.neteast.web.controller.dict;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.dict.DictKey;
import com.neteast.business.domain.dict.DictValue;
import com.neteast.business.domain.dict.vo.DictKeyVO;
import com.neteast.business.service.IDictKeyService;
import com.neteast.business.service.IDictValueService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
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
    IDictValueService dictValueService;

    @GetMapping("/list")
    public AjaxResult getDictKeyList(DictKey dictKey){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<DictKey> list = dictKeyService.getDictKeyList(dictKey);
        TableDataInfo info = getDataTable(list);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/one")
    public AjaxResult getDictKeyOne(DictKey dictKey){

        DictKey temp = dictKeyService.getById(dictKey);
        List<DictValue> values = dictValueService.getKeyValue(temp.getId());
        DictKeyVO dictKeyVO = DictKeyVO.convert(temp);
        dictKeyVO.setValues(values);
        return success(dictKeyVO);
    }

    @PostMapping("/update")
    public AjaxResult updateDictKeyData(@RequestBody DictKey dictKey){

        dictKeyService.save(dictKey);
        return success();
    }

    @PostMapping("/add")
    public AjaxResult addDictKeyData(@RequestBody DictKey dictKey){

        dictKeyService.save(dictKey);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delDictKeyData(@PathVariable Integer id){

        dictKeyService.delDictKey(id);
        return success();
    }
}
