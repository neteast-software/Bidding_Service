package com.neteast.web.controller.dict;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.dict.DictValue;
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
 * @author lzp
 * @date 2023年11月22 11:00
 */

@RestController
@RequestMapping("/dictValue")
public class DictValueController extends BaseController {

    @Resource
    IDictValueService dictValueService;

    @GetMapping("/listByPage")
    public AjaxResult getDictValueListByPage(DictValue dictValue){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<DictValue> values = dictValueService.getDictValueList(dictValue);
        TableDataInfo info = getDataTable(values);
        JSONObject body = initPageParams(info,pageDomain.getPageSize(),pageDomain.getPageNum());
        return success(body);
    }

    @GetMapping("/list")
    public AjaxResult getDictValueList(DictValue dictValue){

        List<DictValue> list = dictValueService.getDictValueList(dictValue);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addDictValueData(@RequestBody DictValue dictValue){

        dictValueService.save(dictValue);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delDictValueData(@PathVariable Integer id){

        dictValueService.delDictValueData(id);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateDictValueData(@RequestBody DictValue dictValue){

        dictValueService.updateDictValue(dictValue);
        return success();
    }

}
