import DataBase.DataBase;
import DataBase.*;
import DataBase.DataBase.Users;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static DataBase.DataBase.INSTANCE;

public class Admin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        // todo sesion ang cookie
        HttpSession session = req.getSession();


        String hashAuth = IDbTable.hashSha256(session.getCreationTime()+"admin"+session.getId());

        session.setAttribute("logined", hashAuth);
        session.setMaxInactiveInterval(120); ///  !!!!!!!!
        ///////////


      req.setAttribute("users",INSTANCE.users.getAll());
        req.setAttribute("sets",INSTANCE.settings.getAll());

      req.getRequestDispatcher("lk_admin.jsp").forward(req,resp);
}


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=   req.getParameter("name");
        resp.getWriter().println("  GET /Admin/   ---"+name);
    }
}
