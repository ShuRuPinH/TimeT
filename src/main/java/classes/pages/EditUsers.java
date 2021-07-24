package classes.pages;

import classes.DataBaseDir.Archiver;
import classes.DataBaseDir.DataBase;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.DataBaseDir.DataBase.INSTANCE;
import static classes.DataBaseDir.Loger.INSTANCE_LOG;


public class EditUsers extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String user = req.getParameter("user");
        System.out.println(" ---- EDIT_USER ----      user:" + user);
        String ban_edt_ent_del = req.getParameter("ban_edt_ent_del");

        switch (ban_edt_ent_del) {
            case "0": {
                DataBase.Users.User tmp = INSTANCE.users.getUser(user);

                if (tmp == null) usErr(req, resp);
                else {
                    tmp.setBlock(!tmp.getBlock());
                    INSTANCE.users.saveAll();
                    INSTANCE_LOG.logWrite("блокировка пользователя    login:" + user);

                    redirect(resp);

                    break;
                }
            }
            case "1": {
                req.setAttribute("userReq", INSTANCE.users.getUser(user));
                req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
                break;
            }
            case "2": {
                req.getSession().setAttribute("user", user);
                INSTANCE_LOG.logWrite("вход под пользователем (admin_jump)    login:" + user);
                req.getRequestDispatcher("/user").forward(req, resp);
                break;
            }
            case "3": {
                if (INSTANCE.users.getUser(user).getAdmin()) {
                    req.setAttribute("error", "Администратора удалить нельзя.");
                    req.setAttribute("er_type", "Ошибка удаления");
                    req.getRequestDispatcher("/gen_error.jsp").forward(req, resp);
                    break;
                }
                if (INSTANCE.users.remove(user) == null) {
                    usErr(req, resp);

                } else {
                    INSTANCE_LOG.logWrite("удаление пользователя    login:" + user);
                    Archiver.archive(user);
                    redirect(resp);
                }
                break;
            }
            default: {
                req.setAttribute("error", "Неправильный запрос.");
                req.setAttribute("er_type", "Ошибка отправки формы");
                req.getRequestDispatcher("/gen_error.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/get.html");
    }

    private void usErr(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", "Запись о пользователе не найдена");
        request.setAttribute("er_type", "Ошибка запроса");
        request.getRequestDispatcher("/gen_error.jsp").forward(request, response);

    }

    private void redirect(HttpServletResponse resp) {
        resp.setHeader("Location", resp.encodeRedirectURL("/admin"));
        resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
    }
}
