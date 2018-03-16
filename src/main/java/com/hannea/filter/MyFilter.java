package com.hannea.filter;

import com.hannea.model.ResourceType;
import com.hannea.service.SecurityService;
import com.hannea.util.SpringUtil;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Class
 *
 * @author wgm
 * @date 2018/03/16
 */
@Configuration
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String reqPath = request.getContextPath() + request.getRequestURI();
        SecurityService securityService = (SecurityService)SpringUtil.getBean("securityService");
        int flag = securityService.hasAuthority(1,1, ResourceType.API.getTypeCode(),reqPath);
        if(flag == 0){
            //没有权限
           return;
        }else {
            doFilter(servletRequest,servletResponse,filterChain);
        }
    }

    @Override
    public void destroy() {

    }
}
