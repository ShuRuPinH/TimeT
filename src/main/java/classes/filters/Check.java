package classes.filters;


import classes.DataBaseDir.DataBase;
import classes.DataBaseDir.IDbTable;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static classes.DataBaseDir.DataBase.INSTANCE;
import static classes.DataBaseDir.Loger.INSTANCE_LOG;
import static classes.core.SMScore.INSTANCE_CORE;


public class Check extends HttpServlet {
    static String realPath="";

    @Override
    public void init() throws ServletException {
        INSTANCE_CORE.getSchedule();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        // sessoin check

        realPath = getServletContext().getRealPath("/");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        //    request.setCharacterEncoding("UTF-8");


        if (session.getAttribute("logined") != null && request.getParameter("auth") == null) {
            String user = (String) request.getAttribute("user");
            String hashA = (String) session.getAttribute("logined");
            //  System.out.println(" auth = "+request.getParameter("auth"));

            String hashAdmin = IDbTable.hashSha256(session.getCreationTime() + "admin" + session.getId());
            request.setAttribute("user", user);


            if (hashA.equals(hashAdmin)) {
                request.getRequestDispatcher("/admin").forward(request, response);
                INSTANCE_LOG.logAutWrite("авторизованая сессия ADMIN    SESSION_ID: " + request.getSession().getId());
                return;
            } else if (user != null) {
                String hashUser = IDbTable.hashSha256(session.getCreationTime() + user + session.getId());
                if (hashA.equals(hashUser)) {
                    request.getRequestDispatcher("/user").forward(request, response);
                    INSTANCE_LOG.logAutWrite("авторизованая сессия USER     SESSION_ID: " + request.getSession().getId());
                    return;
                }
            }
        }


        if (request.getParameter("email") == null || request.getParameter("password") == null
                || request.getParameter("email").trim().equals("") || request.getParameter("password").trim().equals("")) {
            request.setAttribute("error", "Нет имени пользователи или пароля.");
            request.setAttribute("er_type", "Ошибка авторизации");
            //request.getRequestDispatcher("/gen_error.jsp").forward(request,response);
            request.getRequestDispatcher("/gen_error.jsp").forward(request, response);
        } else {
            String em = request.getParameter("email");
            String hash = IDbTable.hashSha256(request.getParameter("password"));
            System.out.println("em = " + em + " hash = " + hash + "      req = " + request);
            if (request.getParameter("password2") != null) {

                String hash2 = IDbTable.hashSha256(request.getParameter("password"));
                if (hash.equals(hash2) && !INSTANCE.users.exists(em)) {

                    INSTANCE_LOG.logAutWrite("создан новый пользователь     :" + em);
                    INSTANCE.users.put(new DataBase.Users.User(em, hash));
                } else {
                    request.setAttribute("error", "Пользователь уже зарегистрирован.");
                    request.setAttribute("er_type", "Ошибка регистрации");
                    request.getRequestDispatcher("/gen_error.jsp").forward(request, response);
                    return;
                }

            }
            DataBase.Users.User temp = INSTANCE.users.findKey(em);
            if (temp == null) {

                INSTANCE_LOG.logAutWrite("не найден пользователь     login: " + request.getParameter("email") );
                authErr(request, response);
            } else {
                session.invalidate();

                request.getSession().setAttribute("user", temp.login);
                request.getSession().setAttribute("realPath", getServletContext().getRealPath("/"));
                System.out.println(" ---- CHECK ----      set Session Attr \"user\":" + temp.login);
                if (temp.password.equals(hash)) {
                    if (temp.getAdmin()) {
                        System.out.println("to /admin " + temp.login);
                        INSTANCE_LOG.logAutWrite("авторизован администратор  login: " + temp.login);

                        //   request.setAttribute("admin", temp);
                        request.getRequestDispatcher("/admin").forward(request, response);
                    } else {
                        System.out.println("to /user " + temp.login);

                        INSTANCE_LOG.logAutWrite("авторизован пользователь   login: " + temp.login);
                        request.getRequestDispatcher("/user").forward(request, response);
                    }
                } else {
                    INSTANCE_LOG.logAutWrite("не правильный пароль       login: " + temp.login);
                    authErr(request, response);
                }


            }

        }

    }

    private void authErr(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        request.setAttribute("error", "Неверно имя пользователя или пароль.");
        request.setAttribute("er_type", "Ошибка авторизации");
        request.getRequestDispatcher("/gen_error.jsp").forward(request, response);

    }

    static public String getRealPath() {
        return realPath;
    }
}
