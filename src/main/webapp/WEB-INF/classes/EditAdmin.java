import DataBase.DataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static DataBase.DataBase.INSTANCE;

public class EditAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String name=   req.getParameter("name");
     String description =   req.getParameter("description");
     Integer value = Integer.valueOf(req.getParameter("value"));
     String add_edt_del = req.getParameter("add_edt_del");
        System.out.println(name+"   "+description+"    "+ value  +"         "+add_edt_del);
     switch (add_edt_del){
         case "0":{
             INSTANCE.settings.put(new DataBase.Settings.Record(name,description,value));
             resp.setHeader("Location", resp.encodeRedirectURL("/admin"));
             resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
             break;
         }
         case "1":{
             DataBase.INSTANCE.settings.remove(name);

             if (! INSTANCE.settings.put(new DataBase.Settings.Record(name,description,value))){
                 req.setAttribute("error", "Не удалось добавить настройку. Вероятно, она уже существует.");
                 req.setAttribute("er_type","Ошибка добавления параметра");
                 req.getRequestDispatcher("/gen_error.jsp").forward(req, resp);
                 return;
             }
             resp.setHeader("Location", resp.encodeRedirectURL("/admin"));
             resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
             break;
         }
         case "2":{
             if (DataBase.INSTANCE.settings.remove(name)==null){
                 req.setAttribute("error", "Не удалось удалить элемент. Вероятно, он уже не существует.");
                 req.setAttribute("er_type","Ошибка удаления параметра");
                 req.getRequestDispatcher("/gen_error.jsp").forward(req, resp);
                 return;
             }
             resp.setHeader("Location", resp.encodeRedirectURL("/admin"));
             resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
             break;
         }
     }


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=   req.getParameter("name");

        resp.getWriter().println("  GET /EditAdmin/   name=!!!"+name);
    }
}
