<%-- 
    Document   : users
    Created on : 27-Mar-2017, 20:50:20
    Author     : nikica1
--%>
<%@page import="bussiness.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if(session.getAttribute("uid")== null)
    response.sendRedirect("login.jsp");

   if(request.getParameter("newUserConfirm")!= null && request.getParameter("newUserNick")!= "" 
           && request.getParameter("newUserPassword")!= ""){
       
       User u = new User(request.getParameter("newUserNick"), request.getParameter("newUserPassword"));
       u.insert();
       
   }
   %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
        <table border="1">
            <tr bgcolor="#d3d3d3">
                <td>Nick name</td><td>Password</td>
            </tr>
            
            <% ArrayList<User> al = User.getAll();
               
                for(User u : al){
             %>
             
             <tr>
                 <td><%=u.nick%></td>
                 <td><%=u.pass%></td>
             </tr>
            <% 
                }
             %>
        </table>
        
        <form action="users.jsp" method="post">
            <table border="1">
                <tr bgcolor="#d3d3d3">
                    <td>New Nick name</td>
                    <td>New Password</td><td></td>
                </tr>
                <tr>
                    <td><input type="text" name="newUserNick" /></td>
                    <td><input type="password" name="newUserPassword" /></td>
                    <td><input type="submit" name="newUserConfirm" value="Confirm" /></td>
                </tr>    
            </table>    
        </form>  
        
    </body>
</html>
