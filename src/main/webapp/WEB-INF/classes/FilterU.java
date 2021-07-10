import DataBaseDir.IDbTable;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FilterU implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        String user = (String) request.getAttribute("user");
        if (user==null) user=request.getParameter("user");// todo  why&how

        String hash= (String) session.getAttribute("logined");

        String hashAuth="";
        if (user!=null) hashAuth = IDbTable.hashSha256(session.getCreationTime()+user+session.getId());


        if(hash==null || !hash.equals(hashAuth) ){

            System.out.println( "FilterU    user :" +user);

                ((HttpServletResponse)response).sendRedirect("/login");

        } else  chain.doFilter(request, response);
    }
}
