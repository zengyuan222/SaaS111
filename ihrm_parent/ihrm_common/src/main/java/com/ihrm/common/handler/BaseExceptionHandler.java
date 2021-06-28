package com.ihrm.common.handler;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的公共异常处理器
 *      1.声明异常处理器
 *      2.对异常统一处理
 *
 */
@ControllerAdvice   // 交由spring框架处理，调用什么的也交又它处理
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)//
    @ResponseBody  // 将Result封装成Json对象响应给前端(这里是类，不是在controller中，所以返回Json数据给前端需要@ResponseBody)
    public Result error(HttpServletRequest request, HttpServletResponse response,Exception e){
        if(e.getClass() == CommonException.class){
            //类型转换
            CommonException ce = (CommonException)e;
            Result result = new Result(ce.getResultCode());
            return result;
        }else {
            Result result = new Result(ResultCode.SERVER_ERROR);
            return result;
        }
    }
}
