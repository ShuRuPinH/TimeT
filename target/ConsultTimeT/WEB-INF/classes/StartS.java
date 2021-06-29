import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class StartS extends GenericServlet {



    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {



      if (req.getAttributeNames().hasMoreElements()) throw new ServletException("NO PARAMS FOR START");

        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();

            writer.println("<h2>Welcome to servlets</h2>");
    }
}
