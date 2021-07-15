package classes.pages;

import classes.DataBaseDir.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static classes.DataBaseDir.DataBase.INSTANCE;

public class Admin extends HttpServlet {

    List<String> listSessions = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        // todo sesion ang cookie

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if (!listSessions.contains(session.getId())){
            String hashAuth = IDbTable.hashSha256(session.getCreationTime()+"admin"+session.getId());

            session.setAttribute("logined", hashAuth);
            session.setAttribute("axe", 104);
            session.setMaxInactiveInterval(120);

            listSessions.add(session.getId());

            String ipAddr = req.getRemoteAddr();
            System.out.println("ipAddr: "+ipAddr);

            String report =(new Date(session.getCreationTime())).toString().substring(0,23)+"\t\tses.ID: "+(session.getId())
                    +"\t\tAdmin aka: " +user;

            System.out.println("-admin-user-= "+user);
            Files.writeString(Path.of(getServletContext().getRealPath("/")+"/history/admin.ses"),
                    report+"\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        }

        String hashAuth = IDbTable.hashSha256(session.getCreationTime()+"admin"+session.getId());

       ///  !!!!!!!!
        ///////////


      req.setAttribute("users",INSTANCE.users.getAll());
        req.setAttribute("sets",INSTANCE.settings.getAll());

      req.getRequestDispatcher("lk_admin.jsp").forward(req,resp);
}


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/get.html");
    }
}
