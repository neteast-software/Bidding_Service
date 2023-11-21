package com.neteast.web.controller.template;

import com.neteast.common.core.controller.BaseController;
import com.neteast.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzp
 * @date 2023年11月21 14:14
 */

@RestController
@RequestMapping("/templateDict")
public class TemplateDictController extends BaseController {


    /**
     * @Description 字典更新，直接插入，获取字典值是
     * @author lzp
     * @Date 2023/11/21
     */
    public AjaxResult updateTemplateDictData(){
        return null;
    }
}
