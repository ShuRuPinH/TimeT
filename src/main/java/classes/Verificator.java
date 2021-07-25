package classes;

import classes.DataBaseDir.IDbTable;
import classes.core.Emailer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static classes.DataBaseDir.DataBase.INSTANCE;
import static classes.DataBaseDir.Loger.INSTANCE_LOG;


@WebServlet("/verif")
public class Verificator extends HttpServlet {

    //static String HOST = "http://localhost:8888/";
    static String HOST = "https://timet.mircloud.ru/";

    private static ConcurrentMap<String, String> valList = new ConcurrentSkipListMap();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        String login = req.getParameter("login");
        String value = req.getParameter("value");

        if (mode.equals("new")) {
            chagePass(login);
        } else if (mode.equals("0110")) {
            String hashPass = IDbTable.hashSha256(req.getParameter("pass"));
            System.out.println(hashPass);

            INSTANCE.users.getUser(req.getParameter("user")).setPassword(hashPass);
            INSTANCE.users.saveAll();

            INSTANCE_LOG.logWrite("изменение пароля    login:" + req.getParameter("user"));


        } else if (mode.equals("rec") && valList.containsKey(login) && valList.get(login).equals(value)) {
            valList.remove(login);
            req.setAttribute("user", login);

            req.getRequestDispatcher("/recover.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("/login");


    }

    public static void chagePass(String email) {


        String val = IDbTable.hashSha256(new Date() + email + Math.random());
        valList.put(email, val);
        String href = null;

        href = HOST + "/verif" + "?mode=rec&login=" + email + "&value=" + val;


        String mail = "<p>&nbsp;</p>\n" +
                "<p><br /><br /><br /></p>\n" +
                "<table style=\"margin-left: auto; margin-right: auto; border-collapse: collapse; width: 50%;\" border=\"0\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td><span style=\"font-size: 24pt; font-family: impact, sans-serif;\"><a href=\"" +
                href
                + "\">Установить новый пароль</a></span></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td><span style=\"font-size: 14pt; font-family: impact, sans-serif;\">" +
                "Нажмите на ссылку выше или скопируйте адрес ниже в  браузер:</span><br>" +
                href
                + "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>";

        Emailer.sendEmail(email, "Восcтановление пароля", mail);

    }

/// не используется
    public static void generateVal(String email) {


        String val = IDbTable.hashSha256(new Date() + email + Math.random());
        valList.put(email, val);
        String href = null;

        href = HOST + "/verif" + "?mode=val&login=" + email + "&value=" + val;


        String mail = "<p>&nbsp;</p>\n" +
                "<p><br /><br /><br /></p>\n" +
                "<table style=\"margin-left: auto; margin-right: auto; border-collapse: collapse; width: 50%;\" border=\"0\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td><span style=\"font-size: 24pt; font-family: impact, sans-serif;\"><a href=\"" +
                href
                + "\">Подтвердить e-mail</a></span></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td><span style=\"font-size: 14pt; font-family: impact, sans-serif;\">" +
                "Нажмите на ссылку выше или скопируйте адрес ниже в  браузер:</span><br>" +
                href
                + "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>";

        Emailer.sendEmail(email, "Подтвердите почту", mail);

    }


}
