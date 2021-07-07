import DataBase.DataBase;
import DataBase.IDbTable;
import com.sun.security.auth.module.UnixLoginModule;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import static DataBase.DataBase.INSTANCE;


public class Check extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session =request.getSession();
    // sessoin check

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");



if (session.getAttribute("logined")!=null && request.getParameter("auth")==null) {
    String user = (String) request.getAttribute("user");
    String hashA =(String) session.getAttribute("logined");
  //  System.out.println(" auth = "+request.getParameter("auth"));

    String hashAdmin = IDbTable.hashSha256(session.getCreationTime()+"admin"+session.getId());


    if (hashA.equals(hashAdmin)){
        request.getRequestDispatcher("/admin").forward(request,response);return;}
    else if (user!= null){
        String hashUser = IDbTable.hashSha256(session.getCreationTime()+user+session.getId());
       if (hashA.equals(hashUser)){
            request.setAttribute("user", user);
            request.getRequestDispatcher("/user").forward(request,response);return;}
    }
}



if (request.getParameter("email")==null ||   request.getParameter("password")==null
        || request.getParameter("email").trim().equals("") || request.getParameter("password").trim().equals("")){
    request.setAttribute("error","Нет имени пользователи или пароля.");
    request.setAttribute("er_type","Ошибка авторизации");
    //request.getRequestDispatcher("/gen_error.jsp").forward(request,response);

    request.getRequestDispatcher("/gen_error.jsp").forward(request,response);
}

else {
  String em =  request.getParameter("email");
  String hash = IDbTable.hashSha256(request.getParameter("password"));
    System.out.println("em = " + em + " hash = "+ hash +"      req = "+request);


    if(request.getParameter("password2")!=null  ){

        String hash2 = IDbTable.hashSha256(request.getParameter("password"));
         if (hash.equals(hash2) && !INSTANCE.users.exists(em)){
             INSTANCE.users.put(new DataBase.Users.User(em,hash));
         }
         else {


             request.setAttribute("error","Пользователь уже зарегистрирован.");
             request.setAttribute("er_type","Ошибка регистрации");

             request.getRequestDispatcher("/gen_error.jsp").forward(request,response);
           return;
         }

    }
   DataBase.Users.User temp = INSTANCE.users.findKey(em);
    if (temp==null){
      authErr(request,response);
    }
    else {
        if (temp.password.equals(hash)) {
           if (temp.getAdmin()) {
               System.out.println("to /admin");

               session.invalidate();
               request.setAttribute("admin", temp);
               request.getRequestDispatcher("/admin").forward(request,response);
           }
           else {
               System.out.println("to /user");
              session.invalidate();
               request.setAttribute("user", temp.getLogin());
               request.getRequestDispatcher("/user").forward(request,response);
           }
        }
        else authErr(request,response);


    }

}

    }

    private void authErr(ServletRequest request , ServletResponse response ) throws ServletException, IOException {
        request.setAttribute("error","Неверно имя пользователя или пароль.");
        request.setAttribute("er_type","Ошибка авторизации");
        request.getRequestDispatcher("/gen_error.jsp").forward(request,response);

    }
}
