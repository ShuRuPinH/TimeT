package classes.pages;

import classes.DataBaseDir.IDbTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.DataBaseDir.DataBase.INSTANCE;
import static classes.DataBaseDir.Loger.INSTANCE_LOG;

public class EditUserParams extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("pass_adm_lim");
        String user = req.getParameter("user");
        req.setAttribute("user", INSTANCE.users.getUser(user));
        switch (mode) {
            case "0": {
                String hashPass = IDbTable.hashSha256(req.getParameter("pass"));
                System.out.println(hashPass);

                INSTANCE.users.getUser(user).setPassword(hashPass);
                INSTANCE.users.saveAll();

                INSTANCE_LOG.logWrite("изменение пароля    login:" + user);

                resp.sendRedirect("/login");
                break;
            }
            case "1": {

                // INSTANCE.users.getUser(user).setAdmin(Boolean.getBoolean(req.getParameter("adm")));
                // INSTANCE.users.saveAll();
                INSTANCE_LOG.logWrite("установка прав администратора ->  login:" + user);
                forrw(req, resp, user);
                break;
            }
            case "2": {
                int temp;

                try {
                    temp = Integer.parseInt(req.getParameter("limit"));
                } catch (Exception e) {
                    e.printStackTrace();
                    temp = 100;
                }
                INSTANCE_LOG.logWrite("изменения лимита смс на " + temp + " шт. в день    login:" + user);
                INSTANCE.users.getUser(user).setLimit(temp);
                INSTANCE.users.saveAll();

                forrw(req, resp, user);
                break;
            }
            default: {


                System.out.println("default  " + mode);
            }
        }


    }

    void forrw(HttpServletRequest req, HttpServletResponse resp, String user) throws ServletException, IOException {
        req.setAttribute("userReq", INSTANCE.users.getUser(user));
        req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/get.html");
    }
}
