package com.neteast.web.controller.dict;

import com.neteast.business.domain.dict.DictType;
import com.neteast.business.service.IDictTypeService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzp
 * @date 2023年12月19 18:15
 */

@RestController
@RequestMapping("/dictType")
public class DictTypeController extends BaseController {

    @Resource
    IDictTypeService dictTypeService;

    @GetMapping("/list")
    public AjaxResult getDictTypeList(){

        List<DictType> list = dictTypeService.lambdaQuery().list();
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addDictType(@RequestBody DictType dictType){

        dictTypeService.save(dictType);
        return addSuccess();
    }

    @PostMapping("/update")
    public AjaxResult updateDictType(@RequestBody DictType dictType){
        dictTypeService.updateById(dictType);
        return updateSuccess();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delDictType(@PathVariable("id")Integer id){
        dictTypeService.removeDictType(id);
        return delSuccess();
    }
}
