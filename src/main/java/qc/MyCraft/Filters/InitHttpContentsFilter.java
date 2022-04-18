package qc.MyCraft.Filters;

import Common.ThreadLocalUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *初始化http中的 request和response
 */
public class InitHttpContentsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ThreadLocalUtils.setRequest((HttpServletRequest)servletRequest);
        ThreadLocalUtils.setResponse((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
