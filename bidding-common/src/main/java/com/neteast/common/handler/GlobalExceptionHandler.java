package com.neteast.common.handler;

import cn.hutool.core.lang.Validator;
import cn.hutool.http.server.HttpServerRequest;
import com.neteast.common.core.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzp
 * @date 2023年11月15 13:46
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AjaxResult handleRuntimeException(RuntimeException e, HttpServletRequest request){

        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        String message = e.getMessage();
        return AjaxResult.error(Validator.hasChinese(message)?message:"系统发生未知异常");
    }

}
