package com.neteast.common.annotation;

import java.lang.annotation.*;

/**
 * @Description 数据字典处理类
 * @author lzp
 * @Date 2024/1/2
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DictData {
    /**
     * 方法描述 字典类型
     * @return
     */
    String[] dictType() default {};

    /**
     * 方法描述 获取字典值的字段（用于将字典转义存到额外字段时使用）
     */
    String valueField() default "";

    /**
     * 方法描述 是否替换自己的值
     */
    boolean isReplaceSelf() default true;

    /**
     * 方法描述 目标字段（用于将字典转义存到额外字段时使用）
     */
    String targetField() default "";

}
