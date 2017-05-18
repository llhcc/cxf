<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="org.acegisecurity.context.SecurityContextHolder"%>  
<%@ page import="org.acegisecurity.userdetails.*"%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
    <head>  
        <title>User Info Page</title>  
  
        <meta http-equiv="pragma" content="no-cache">  
        <meta http-equiv="cache-control" content="no-cache">  
        <meta http-equiv="expires" content="0">  
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
        <meta http-equiv="description" content="This is my page">  
    </head>  
  
    <body>  
        当前用户:  
        <%  
            Object obj = SecurityContextHolder.getContext().getAuthentication();          
            if (null != obj){  
                Object userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
                String username = "";  
                if (userDetail instanceof UserDetails) {  
                    username = ((UserDetails) userDetail).getUsername();  
                } else {  
                    username = userDetail.toString();  
                }  
                out.print(username);  
                out.print("<br><a href=\"j_acegi_logout\">注销</a>");  
            }else{  
                out.print("当前没有有效的用户");  
                out.print("<br><a href=\"acegilogin.jsp\">登陆</a>");  
            }  
        %>         
    </body>  
</html>  