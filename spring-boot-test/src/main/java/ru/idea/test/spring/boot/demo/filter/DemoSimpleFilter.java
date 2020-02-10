package ru.idea.test.spring.boot.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class DemoSimpleFilter implements Filter {

    private static Logger LOGGER = LoggerFactory.getLogger(DemoSimpleFilter.class);
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
        LOGGER.error("DoFilter - Remote Host:"+request.getRemoteHost());
        LOGGER.error("DoFilter - Remote Address:"+request.getRemoteAddr());
        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {}
}