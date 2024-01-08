package com.neteast.web.controller.dict;

import com.alibaba.fastjson2.JSONObject;
import com.neteast.business.domain.dict.PlusesCondition;
import com.neteast.business.domain.dict.vo.PlusesConditionVO;
import com.neteast.business.service.IPlusesConditionService;
import com.neteast.business.service.ISysDynamicRenderingService;
import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import com.neteast.common.core.page.PageDomain;
import com.neteast.common.core.page.TableDataInfo;
import com.neteast.common.core.page.TableSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 附加项内容 (环保加分等)
 * @author lzp
 * @date 2023年12月15 11:48
 */

@RestController
@RequestMapping("/plusesCondition")
public class PlusesConditionController extends BaseController {

    @Resource
    IPlusesConditionService plusesConditionService;

    @Resource
    ISysDynamicRenderingService sysDynamicRenderingService;

    @GetMapping("/listByPage")
    public AjaxResult getPlusesConditionListByPage(PlusesConditionVO plusesConditionVO){

        startPage();
        PageDomain pageDomain = TableSupport.getPageDomain();
        List<PlusesConditionVO> list = plusesConditionService.getPlusesConditionList(plusesConditionVO);
        TableDataInfo info = getDataTable(list);
        JSONObject rendering = sysDynamicRenderingService.getSysDynamicRendering("project","plusesCondition","list");
        initPageParams(rendering,info, pageDomain.getPageSize(), pageDomain.getPageNum());
        return success(rendering);
    }

    @GetMapping("/list")
    public AjaxResult getPlusesConditionList(PlusesConditionVO plusesConditionVO){

        List<PlusesConditionVO> list = plusesConditionService.getPlusesConditionList(plusesConditionVO);
        return success(list);
    }

    @PostMapping("/add")
    public AjaxResult addPlusesCondition(@RequestBody PlusesConditionVO plusesConditionVO){

        PlusesCondition conditions = PlusesConditionVO.convert(plusesConditionVO);
        conditions.setDel(1);
        plusesConditionService.save(conditions);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updatePlusesCondition(@RequestBody PlusesConditionVO plusesConditionVO){

        PlusesCondition condition = PlusesConditionVO.convert(plusesConditionVO);
        plusesConditionService.updateById(condition);
        return success();
    }

    @PostMapping("/del/{id}")
    public AjaxResult delPlusesCondition(@PathVariable("id") Integer id){

        plusesConditionService.delPlusesCondition(id);
        return success();
    }

}
