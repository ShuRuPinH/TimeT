/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-07-21 18:48:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class editUser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "html_parts/before_title.jspf", out, false);
      out.write("\r\n");
      out.write("<title>Редактирование пользователя</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<form name=\"edit_user_params\" id=\"edit_user_params\" method=\"POST\"  accept-charset=\"UTF-8\" action=\"/edit_user_params\">\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"mx-auto text-center col-md-8\">\r\n");
      out.write("            <input hidden=\"hidden\" name=\"user\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userReq.login}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("            <input hidden=\"hidden\" name=\"pass_adm_lim\" id=\"pass_adm_lim\">\r\n");
      out.write("            <h3 style=\"font-weight: bolder\" class=\"display-4\">Редактирование пользователя</h3>\r\n");
      out.write("            <hr>  ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userReq.login}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("            <hr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <p class=\"lead text-primary\">Изменить параметры  пользователя, посмотреть его сессии и\r\n");
      out.write("                напоминания.</p>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"contentContainer\">\r\n");
      out.write("    <div style=\"background-color: grey; margin-bottom: 10px\" class=\"row\">\r\n");
      out.write("        <div class=\"col-md-3\">\r\n");
      out.write("            <h5 style=\"color: white\" class=\"\">Параметр</h5>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"col-md-3\">\r\n");
      out.write("            <h5 style=\"color: white\" class=\"\">Текущее значение</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-4\">\r\n");
      out.write("            <h5 style=\"color: white\" class=\"\">Новое значение</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-2\">\r\n");
      out.write("            <h5 style=\"color: white\" class=\"\">Действие</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-md-3\">\r\n");
      out.write("            <h5 class=\"\">Пароль</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-3\">\r\n");
      out.write("            <h5 class=\"\">* * * * *</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-4\">\r\n");
      out.write("            <h5 class=\"\"><input type=\"text\" name=\"pass\"/></h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-2\">\r\n");
      out.write("            <div class=\"dropdown\">\r\n");
      out.write("                <button style=\"margin-bottom: 5px\" class=\"btn btn-warning \" onclick=\"check()\" value=\"0\" type=\"button\">\r\n");
      out.write("                    Изменить\r\n");
      out.write("\r\n");
      out.write("                </button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-md-3\">\r\n");
      out.write("            <h5 class=\"\">Администратор?</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-3\">\r\n");
      out.write("            <h5 class=\"\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userReq.admin}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-4\">\r\n");
      out.write("            <h5 class=\"\"><select name=\"adm\" class=\"form-select\" aria-label=\"Default select example\">\r\n");
      out.write("                <option selected>True - админ, False - юзер</option>\r\n");
      out.write("                <option value=\"true\">True</option>\r\n");
      out.write("                <option value=\"false\">False</option>\r\n");
      out.write("\r\n");
      out.write("            </select></h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-2\">\r\n");
      out.write("            <div class=\"dropdown\">\r\n");
      out.write("                <button disabled=\"disabled\" style=\"margin-bottom: 5px\" class=\"btn btn-warning \" onclick=\"document.getElementById('pass_adm_lim').value=1;\" type=\"submit\">\r\n");
      out.write("                    Изменить\r\n");
      out.write("                </button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-md-3\">\r\n");
      out.write("            <h5 class=\"\">Лимит СМС в день</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-3\">\r\n");
      out.write("            <h5 class=\"\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userReq.limit}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-4\">\r\n");
      out.write("            <h5 class=\"\"><input  type=\"number\" min=\"0\" max=\"2000\" name=\"limit\"/></h5>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-2\">\r\n");
      out.write("            <div class=\"dropdown\">\r\n");
      out.write("                <button  style=\"margin-bottom: 5px\" class=\"btn btn-warning \" onclick=\"document.getElementById('pass_adm_lim').value=2;\" type=\"submit\">\r\n");
      out.write("                    Изменить\r\n");
      out.write("                </button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div></form>\r\n");
      out.write("<div class=\"d-grid gap-2 col-6 mx-auto\"><hr>\r\n");
      out.write("    <button class=\"btn btn-dark\" onclick=\"history.back()\" type=\"button\">Н А З А Д</button>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<script src=\"JS/change_pass.js\"></script>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "html_parts/bottom.jspf", out, false);
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
