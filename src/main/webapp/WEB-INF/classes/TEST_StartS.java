import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
 import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Map;


@WebServlet("/start")
public class TEST_StartS extends HttpServlet {



    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {





        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();

        writer.println("<h2>Welcome to servlets</h2>");
        writer.println("<br>req.getContentLength() = " + req.getContentLength());
        writer.println("<br>req.getContentType() = " + req.getContentType());
        writer.println("<br>req.getProtocol() = " + req.getProtocol());
        writer.println("<br>req.getRemoteAddr() = " + req.getRemoteAddr());
        writer.println("<br>req.getScheme() = " + req.getScheme());

        writer.println("<br>req.getAttribute(\"test\") = " + req.getAttribute("test"));
        writer.println("<br>req.getParameter(\"test\") = " + req.getParameter("test"));
        writer.println("");
            for(Map.Entry<String, String[]> e: req.getParameterMap().entrySet()){
               String rez="";
                for (String s : e.getValue()){
                    rez+="+"+ s;
                }
                writer.println("<br>req.getParameter(\""+e.getKey()+"\") = "+  rez);
        }
            HttpServletRequest tmp =(HttpServletRequest) req;
        Cookie[] cookies= tmp.getCookies();
     for (Cookie c: cookies){
         writer.println("<br>cookie (\""+c.getName()+"\") = "+  c.getValue());
     }
        writer.println("");
        SessionCookieConfig sessionCookieConfig=req.getServletContext().getSessionCookieConfig();
        writer.println("<br>req.getServletContext().getSessionCookieConfig() :");
        writer.println("<br>sessionCookieConfig.getDomain() = " + sessionCookieConfig.getDomain());
        writer.println("<br>resessionCookieConfig.getName() = " + sessionCookieConfig.getName());
        writer.println("<br>sessionCookieConfig.getMaxAge() = " + sessionCookieConfig.getMaxAge());
        writer.println("<br>sessionCookieConfig.getComment() = " + sessionCookieConfig.getComment());
        writer.println("<br>sessionCookieConfig.getPath() = " + sessionCookieConfig.getPath());





        writer.println("<br> = " + req);


        writer.println("<h2>end of text</h2>");
    }
}
