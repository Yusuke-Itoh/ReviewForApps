package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import rfa.JumsHelper;
import beans.DataBeans;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

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
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>ログイン</title>\n");
      out.write("            <style>\n");
      out.write("            label,input \n");
      out.write("            {\n");
      out.write("                display: block;\n");
      out.write("                width: 150px;\n");
      out.write("                float: left;\n");
      out.write("                margin-bottom: 10px;\n");
      out.write("            }\n");
      out.write("            label {\n");
      out.write("                text-align: right;\n");
      out.write("                padding-right: 15px;\n");
      out.write("            }\n");
      out.write("            br { clear: left; }\n");
      out.write("            #btnSubmit{\n");
      out.write("                margin-left: 170px;\n");
      out.write("            }\n");
      out.write("            #div{\n");
      out.write("                margin-left: 220px;\n");
      out.write("            }\n");
      out.write("            </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form action=\"LoginResult\" method=\"POST\">\n");
      out.write("            <label for=\"name\">ユーザー名</label><input id=\"name\" name=\"name\"><br />\n");
      out.write("            <label for=\"password\">パスワード</label><input id=\"password\" name=\"password\"><br />\n");
      out.write("            <input id=\"btnSubmit\" type=\"submit\" name=\"btnSubmit\" value=\"ログイン\"><br>\n");
      out.write("            <input type=\"hidden\" name=\"ac\"  value=\"");
      out.print( hs.getAttribute("ac"));
      out.write("\">\n");
      out.write("            </form>\n");
      out.write("            \n");
      out.write("        <form action=\"Registration\" method=\"POST\">\n");
      out.write("            <input id=\"btnSubmit\" type=\"submit\" name=\"btnSubmit\" value=\"新規会員登録\">\n");
      out.write("            <input type=\"hidden\" name=\"ac\"  value=\"");
      out.print( hs.getAttribute("ac"));
      out.write("\">\n");
      out.write("        </form>\n");
      out.write("        \n");
      out.write("        <br><br>\n");
      out.write("        <div id=\"div\">");
      out.print(jh.home());
      out.write("</div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
