<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>  
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>  
<%@ page import="org.acegisecurity.AuthenticationException" %>  
<html>  
  <head>  
    <title>Login Page</title>  
  </head>  
  <body>  
    <h1>Login</h1>  
  
    <%  
        String strError = request.getParameter("login_error");        
        if (null != strError){   
     %>  
      <font color="red">  
        你的登陆失败,请重试。<BR><BR>  
         原因: <%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>  
      </font>  
      <%  
        }//end if  
      %>  
  
    <form action="j_acegi_security_check" method="POST">  
      <table>  
        <tr><td>User:</td><td><input type='text' name='j_username' value='<%= session.getAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY) %>'></td></tr>  
        <tr><td>Password:</td><td><input type='password' name='j_password'></td></tr>  
        <tr><td><input type="checkbox" name="_acegi_security_remember_me"></td><td>2周内自动登录</td></tr>  
  
        <tr><td colspan='2'><input name="submit" type="submit"></td></tr>  
        <tr><td colspan='2'><input name="reset" type="reset"></td></tr>  
      </table>  
    </form>  
  
  </body>  
</html>  