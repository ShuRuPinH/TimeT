import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet ("/test")
public class Test  extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();


 Cookie cookie =new Cookie(req.getParameter("cookie"), req.getParameter("val"));


        resp.setContentType("text/html");


 if (req.getParameter("domain") != null || req.getParameter("domain").equals(" ")  ){

     cookie.setDomain(req.getParameter("domain"));
 }
        req.setAttribute("is_del",false);
      for(Cookie c: req.getCookies()){
          if (c.getName().equals("del")){
              req.setAttribute("is_del",true);
              c.setMaxAge(0);
              resp.addCookie(c);
          }
      }

        Enumeration enumeration =session.getAttributeNames();
        List <String> listArgs = new ArrayList<>();



        listArgs.add("miT)  "+ session.getMaxInactiveInterval());
        listArgs.add("ID)  "+ session.getId());
        listArgs.add("crT)  "+ session.getCreationTime());
        listArgs.add("laT)  "+ session.getLastAccessedTime());




        session.setAttribute("list",listArgs);

        session.setAttribute("222","listArgs");
        session.setMaxInactiveInterval(30);
      resp.addCookie(cookie);
       req.getRequestDispatcher("test.jsp").forward(req,resp);
    }

   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }*/
}
