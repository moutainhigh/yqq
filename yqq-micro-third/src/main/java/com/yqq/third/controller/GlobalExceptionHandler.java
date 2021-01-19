/*
 * 文 件 名:  GlobalExceptionHandler.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2020年3月5日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.controller;

import com.yqq.framework.web.result.JSONResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <全局异常处理>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public JSONResult<?> MyExceptionHandle(MethodArgumentNotValidException exception){
		JSONResult<?> jsonResult = new JSONResult<>();
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            jsonResult.setErrorInfo(fieldErrors.get(0).getDefaultMessage());
        } 
        return jsonResult;
    }
}
