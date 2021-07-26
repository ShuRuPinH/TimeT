package classes.pages;

import classes.DataBaseDir.DataBase;
import classes.DataBaseDir.NoteBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static classes.DataBaseDir.DataBase.INSTANCE;
import static classes.DataBaseDir.Loger.INSTANCE_LOG;
import static classes.core.SMScore.INSTANCE_CORE;

public class EditNotes extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("user", request.getParameter("user"));

        if (request.getParameter("delete") != null) {
            DataBase.Notes.Key kew = new DataBase.Notes.Key(request.getParameter("user"),
                    Long.parseLong(request.getParameter("create")), Long.parseLong(request.getParameter("start")));


            INSTANCE_LOG.logWrite("удалено напоминание от " + new Date(Long.parseLong(request.getParameter("create"))) + "   login: " + request.getParameter("user"));
            INSTANCE.notes.remove(kew);
            INSTANCE_CORE.getSchedule();


        } else {

            System.out.println(" ==== EDIT notes ====  req/datetime = "+request.getParameter("datetime"));

            DataBase.Notes.Value temp = NoteBuilder.build(request.getParameter("user"), request.getParameter("phone"), request.getParameter("mess"),
                    request.getParameter("datetime"), request.getParameter("reps"), request.getParameter("intr"));

            INSTANCE_LOG.logWrite("добавлено напоминание :" + request.getParameter("mess") + "   login: " + request.getParameter("user"));

            INSTANCE.notes.put(temp);
            INSTANCE_CORE.getSchedule();
        }

        redirect(response);

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/get.html");
    }

    private void redirect(HttpServletResponse resp) {
        resp.setHeader("Location", resp.encodeRedirectURL("/user"));
        resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
    }
}
