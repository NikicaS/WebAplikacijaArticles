<%-- 
    Document   : login
    Created on : 27-Mar-2017, 20:48:39
    Author     : nikica1
--%>
<%@page import="bussiness.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if(request.getParameter("nickname")!=null && request.getParameter("password")!=null){
    int userid = User.checkUser(request.getParameter("nickname"),request.getParameter("password"));
    
    if(userid>0){
      session.setAttribute("uid", userid);
      response.sendRedirect("articles.jsp");
    }
    else
        out.print("invalid user");
    
}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
        <form action="login.jsp" method="post">
            User: <input type="text" name="nickname" /><br/>
            Password: <input type="password" name="password" /><br/>
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
