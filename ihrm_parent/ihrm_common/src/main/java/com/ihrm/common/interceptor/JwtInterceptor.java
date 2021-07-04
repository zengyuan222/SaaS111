package com.ihrm.common.interceptor;


import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *      继承父类  HandlerInterceptorAdapter
 *
 *      preHandle: 进入到控制器方法之前执行的内容
 *          boolean:
 *              true: 可以继续执行控制器方法
 *              false: 拦截了
 *      postHandle: 执行控制器方法之后执行的内容
 *      afterCompletion: 响应结束之前执行的内容
 *
 *
 *
 *  1.简化获取token数据的代码编写
 *      统一的用户权限校验（是否登录）
 *  2.判断用户是否具有当前访问接口的权限
 *
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    /**
     *  简化获取token数据的代码编写（判断是否登录）
     *      1.通过request获取请求token信息
     *      2.从token中解析获取claims
     *      3.将claims绑定到request域中
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override    //  执行某个方法先进入这个方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 通过拦截器获取到token数据
         */
        //1.通过request获取请求token信息
        String authorization = request.getHeader("Authorization");
        //判断请求头信息是否为空，或者是否已Bearer开头
        if( !StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")){    // startsWith : 测试此字符串是否以指定的前缀开始。
            //获取token数据
            String token = authorization.replace("Bearer", "");
            Claims claims = jwtUtils.parsetJwt(token);
            if(claims != null ){
                request.setAttribute("user_claims",claims);  // 绑定到request域
                return true;
            }
        }
        throw new CommonException(ResultCode.UNAUTHENTICATED);

    }

    /**
     *   暂时不用

     //  postHandle: 执行控制器方法之后执行的内容
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

     //  afterCompletion: 响应结束之前执行的内容
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }


     */
}
