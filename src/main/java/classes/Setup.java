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
// инсталяционый сервлет, создает системные папки
public class Setup extends HttpServlet {
    static String realPath;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String err="";
        String realPath1 = getServletContext().getRealPath("/");
        realPath=realPath1;
        try {
            Files.createDirectory(Path.of(realPath1+"/dir_log")); //todo all system folders
            Files.createDirectory(Path.of(realPath1+"/history"));
            Files.createDirectory(Path.of(realPath1+"/archive"));
        } catch (IOException e) {

            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            err=errors.toString();
        }
    resp.getWriter().println(err+"\n ===========DONE ================       realPath: "+realPath1);
    }



}

