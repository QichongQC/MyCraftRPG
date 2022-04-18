package qc.MyCraft.Filters;

import Common.UserManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminAuthFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    /**
     * 这部分功能没问题 ，但是我使用 aop替换了
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request1=(HttpServletRequest)servletRequest;
        HttpServletResponse res1=(HttpServletResponse) servletResponse;
        HttpSession session = request1.getSession();
        Object userStatus = UserManager.getUserStatus(session);
        if (userStatus==null)
        {
            session.setAttribute("page_error","无效的访问！");
            res1.sendRedirect("/loginAdmin");
            return;
        }
        filterChain.doFilter(request1, res1);
    }

    public void destroy() {
    }

}
