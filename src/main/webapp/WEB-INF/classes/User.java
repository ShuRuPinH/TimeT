import DataBaseDir.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static DataBaseDir.DataBase.INSTANCE;

public class User extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = (String) req.getAttribute("user");

        // todo sesion ang cookie
        HttpSession session = req.getSession();

        String hashAuth = IDbTable.hashSha256(session.getCreationTime()+user+session.getId());
          session.setAttribute("logined", hashAuth);
        session.setMaxInactiveInterval(120); ///  !!!!!!!!
        ///////////



        resp.getWriter().println("  POST /User/   ---");


        req.setAttribute("notes", NoteBuilder.sortNotes(INSTANCE.notes.select(e -> e.user.equals( req.getAttribute("user")))));
       req.getRequestDispatcher("lk_user.jsp").forward(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=   req.getParameter("name");
        resp.getWriter().println("  GET /User/   ---"+name);
    }
}
