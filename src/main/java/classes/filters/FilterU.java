package classes.filters;

import classes.DataBaseDir.IDbTable;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static classes.DataBaseDir.DataBase.INSTANCE;

public class FilterU implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = ((HttpServletRequest)request).getSession();
        String user = (String) session.getAttribute("user");

        String hash= (String) session.getAttribute("logined");

        String hashAuth="";
        if (user!=null) hashAuth = IDbTable.hashSha256(session.getCreationTime()+user+session.getId());


        if(hash==null || !hash.equals(hashAuth) ){

            System.out.println(" ---- FilterU to login ----     user:"+user);

                ((HttpServletResponse)response).sendRedirect("/login");

        }

        else  chain.doFilter(request, response);
    }
}
