/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-07-20 22:33:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Авторизация</title>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<br><br>\r\n");
      out.write("<div class=\"container h-100\">\r\n");
      out.write("    <div class=\"row\" style=\"height:20%\">\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"row \" style=\"height:30%\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <div class=\"col-3\">\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"shadow p-3 mb-5 bg-white rounded col-6\">\r\n");
      out.write("            <div style=\"text-align: center\" class=\"d-grid gap-2\">\r\n");
      out.write("                <h3> Авторизация / регистрация</h3>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"tab-content\">\r\n");
      out.write("                <div id=\"signInTab\" class=\"tab-pane fade show active\">\r\n");
      out.write("                    <br>\r\n");
      out.write("                    <form id=\"main_form\" name=\"avtor\" accept-charset=\"UTF-8\" method=\"post\" action=\"/check\"  >\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"form-group input-group\">\r\n");
      out.write("\r\n");
      out.write("                            <input type=\"email\" id=\"email\" name=\"email\" class=\"form-control\"  aria-label=\"Default\"\r\n");
      out.write("                                   aria-describedby=\"inputGroup-sizing-default\" placeholder=\"Введите Вашу почту\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <br>\r\n");
      out.write("                        <div class=\"form-group input-group\">\r\n");
      out.write("\r\n");
      out.write("                            <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" aria-label=\"Default\"\r\n");
      out.write("                                   aria-describedby=\"inputGroup-sizing-default\" placeholder=\"Введите Ваш пароль\"\r\n");
      out.write("                                   data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Минимум 8 символов.\r\n");
      out.write("Обязательно присутствие строчных и заглавных латинских букв,\r\n");
      out.write("цифр, и специальных или кириллических символов.\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <br>\r\n");
      out.write("                        <div style=\"text-align: right\" class=\"row\">\r\n");
      out.write("                            <div class=\"col-12\">\r\n");
      out.write("                                <div id=\"2nd_pass\" class=\"custom-control custom-checkbox \">\r\n");
      out.write("\r\n");
      out.write("                                    <span style=\"color: #326ba8; font-weight: bolder \" onclick=\"reg()\">\r\n");
      out.write("                                        зарегистрироваться\r\n");
      out.write("                                    </span>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"d-grid gap-2\">\r\n");
      out.write("                            <input type=\"hidden\" name=\"auth\" value=\"0102\">\r\n");
      out.write("                            <button type=\"button\"  class=\" btn btn-primary shadow \" onclick=\"check()\" >В О Й Т И</button>\r\n");
      out.write("                            <a href=\"forget.html\"style=\"    margin-left: auto;\r\n");
      out.write("    margin-right: auto;\"   class=\"  brand-link\">Не помню пароль ;(</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-3\">\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"JS/dig.js\">\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
