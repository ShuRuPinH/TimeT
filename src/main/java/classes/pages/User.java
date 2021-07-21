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
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static classes.DataBaseDir.DataBase.INSTANCE;
import static classes.DataBaseDir.Loger.INSTANCE_LOG;

public class User extends HttpServlet {

    List <String> listSessions = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        // todo sesion ang cookie
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        System.out.println(" ---- USER ----      user:"+user);
        if (!listSessions.contains(session.getId())){
            listSessions.add(session.getId());
            INSTANCE.users.saveAll();
           INSTANCE.users.getUser(user).sessions.add(session.getCreationTime());

            String enter = session.getAttribute("axe")!=null? "admin_jump" : "user_login";
            String report =(new Date(session.getCreationTime())).toString().substring(0,22)+" ses.ID: "+(session.getId()).substring(0,7)+"...  mode:"+enter;

            System.out.println("--user-= "+user);
            Files.writeString(Path.of(getServletContext().getRealPath("/")+"/history/"+user.replace(".","_")+".ses"),
                    report+"\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        }


        String hashAuth = IDbTable.hashSha256(session.getCreationTime()+user+session.getId());
          session.setAttribute("logined", hashAuth);
        session.setMaxInactiveInterval(60*10); ///  !!!!!!!!
        ///////////
    if (INSTANCE.users.getUser(user).block){
        System.out.println("Block : "+ INSTANCE.users.getUser(user).block);
            (resp).sendRedirect("/banned.html");
        return;
        }


        resp.getWriter().println("  POST /User/   ---");


        req.setAttribute("notes", NoteBuilder.sortNotes(INSTANCE.notes.select(e -> e.user.equals(user))));

        INSTANCE_LOG.logWrite("начало сессии пользователя    login:" +user+"   SESSION_ID:"+session.getId());
       req.getRequestDispatcher("lk_user.jsp").forward(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/get.html");;
    }
}
