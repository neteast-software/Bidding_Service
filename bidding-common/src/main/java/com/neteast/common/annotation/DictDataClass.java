package com.neteast.common.annotation;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited

/**
 * @Description 说明：用来标识返回对象集合需要字典转义，本功能目前只支持bean对象的属性翻译
 * @author lzp
 * @Date 2024/1/2
 */
public @interface DictDataClass {
}
