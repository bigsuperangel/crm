/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.70
 * Generated at: 2017-04-13 11:17:02 UTC
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

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
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

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n");
      out.write("<HTML>\r\n");
      out.write("<HEAD>\r\n");
      out.write("\t<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("<TITLE>欢迎登陆crm客户关系管理系统</TITLE>\r\n");
      out.write("<META http-equiv=Content-Type content=\"text/html; charset=utf-8\">\r\n");
      out.write("<LINK href=\"");
      out.print(path);
      out.write("/images/login.css\" type=text/css rel=stylesheet>\r\n");
      out.write("<script>\r\n");
      out.write("\twindow.onload = function(){\r\n");
      out.write("\t\tvar error=\tgetParameter('error');\r\n");
      out.write("\t\tif(error!=-1){\r\n");
      out.write("\t\t\talert(decodeURI(error));\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction getParameter(name){\r\n");
      out.write("\t   var results = new RegExp('[\\\\?&]' + name + '=([^&#]*)').exec(window.location.href); \r\n");
      out.write("\t   if (!results) { return \"-1\"; }\r\n");
      out.write("\t   return results[1]||\"-1\";\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction changeCode() { \r\n");
      out.write("\t\tvar myimg = document.getElementById(\"code\"); \r\n");
      out.write("\t\tnow = new Date(); \r\n");
      out.write("\t\tmyimg.src=\"CertPic.jsp?code=\"+now.getTime();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction checkForm(){\r\n");
      out.write("\t\tif(document.getElementById(\"username\").value == \"\"){\r\n");
      out.write("\t\t\talert(\"用户名不能为空！\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} \r\n");
      out.write("\t\tif(document.getElementById(\"password\").value == \"\"){\r\n");
      out.write("\t\t\talert(\"密码不能为空！\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.getElementById(\"cretCode\").value == \"\"){\r\n");
      out.write("\t\t\talert(\"验证码不能为空！\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction focuseOn(){\r\n");
      out.write("\t\tdocument.getElementById(\"username\").focus();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//出iframe的页面设置\r\n");
      out.write("\tif(window !=top){\r\n");
      out.write("\t\ttop.location.href=location.href;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</HEAD>\r\n");
      out.write("<BODY onload=\"focuseOn();\">\r\n");
      out.write("<form action=\"crm/login\" method=\"post\" onsubmit=\"return checkForm();\">\r\n");
      out.write("  <table width=\"800\" height=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-bottom:-12px\">\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td>&nbsp;</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td height=\"496\" id=\"main\"><table width=\"639\" height=\"264\" border=\"0\" align=\"center\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"234\" height=\"96\">&nbsp;</td>\r\n");
      out.write("          <td width=\"389\">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td>&nbsp;</td>\r\n");
      out.write("          <td align=\"center\">\r\n");
      out.write("          <div id=\"dv\"><span>用户名：</span><INPUT class=\"textbox\" type=\"text\" id=\"username\" name=\"username\" value=\"admin\"></div>\r\n");
      out.write("          <div id=\"dv\"><span>密　码：</span><INPUT class=\"textbox\" type=\"password\" id=\"password\" name=\"password\" value=\"admin\"></div>\r\n");
      out.write("          <div id=\"dvs\" align=\"left\"><span>验证码：</span><input class=\"textbox\" id=\"cretCode\" name=\"certCode\" type=\"text\" id=\"chknumber\" maxlength=\"4\" value=\"暂不输入\" style=\"width:55px; height:20px\"/>\r\n");
      out.write("\t\t\t\t<img id=\"code\" src=\"CertPic.jsp\" width=\"60px\" height=\"20px\" align=\"top\" alt=\"看不清，点我\" onclick=\"javascript:changeCode()\"/>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div align=\"right\" id=\"dv\"><input type=\"submit\" id=\"btLogin\" value=\"登录\"></div>\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td height=\"104\" id=\"root\">&nbsp;</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("</form>\r\n");
      out.write("</BODY>\r\n");
      out.write("</HTML>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}