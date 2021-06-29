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
        HttpSession session = ((HttpServletRequest)request).getSession();
        String logined =(String) session.getAttribute("logined");
        System.out.println("logined = "+logined);

       if(logined==null || !logined.equals("admin")){

              ((HttpServletResponse)response).sendRedirect("/login");
       } ;

        chain.doFilter(request, response);
    }
}
