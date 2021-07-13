package classes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static classes.DataBaseDir.DataBase.INSTANCE;

@WebServlet ("/setup")
public class TEST_DD extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String err="";

        try {
            Files.createDirectory(Path.of("/history123")); //todo all system folders
        } catch (IOException e) {

            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            err=errors.toString();
        }
    resp.getWriter().println(err+"\n ===========DONE ================");
    }




}

