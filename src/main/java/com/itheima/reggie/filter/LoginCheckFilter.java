package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Java不良人
 * @date 2023/3/27
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配符  支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1  获取本次请求的URI
        String requestURI = request.getRequestURI();
        //log.info("拦截到请求: {}",requestURI);
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        //2  判断本次请求是否需要处理
        boolean check = check(urls,requestURI);
        //3  不需要处理  直接放行
        if(check){
            //log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        //4-1、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }

        //4-2、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("user") != null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("user"));

            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request,response);
            return;
        }
        //5  未登录则返回未登录结果
        //log.info("未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 路径匹配
     * @param urls
     * @param requestURI
     * @return
     */
    private boolean check(String[] urls, String requestURI) {
        for(String url : urls){
            boolean match = PATH_MATCHER.match(url,requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
