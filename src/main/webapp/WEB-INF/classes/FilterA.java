import DataBase.IDbTable;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class FilterA implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = ((HttpServletRequest)request).getSession();
        String hash =(String) session.getAttribute("logined");
        String hashAuth = IDbTable.hashSha256(session.getCreationTime()+"admin"+session.getId());

        System.out.println("hash = "+hash+"            hashAuth = "+hashAuth);
       if(hash==null || !hash.equals(hashAuth)){

              ((HttpServletResponse)response).sendRedirect("/login");
       } else   chain.doFilter(request, response);
    }
}
