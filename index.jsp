<%-- 
    Document   : index
    Created on : 27-Mar-2017, 20:47:07
    Author     : nikica1
--%>
<%@page import="bussiness.User"%>
<%@page import="bussiness.Article"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
        <% if(request.getParameter("aid")!= null){
            Article a = Article.getById(Integer.parseInt(request.getParameter("aid")));
        %>
        <div style="border: 1px solid black; padding: 10px; background-color: #d3d3d3;">
            <div style="font-size: 24px;"><%=a.title%></div>
            <div style="font-size: 14px;">Author: <%=User.getById(a.author).nick%> Realise date: <%=a.rDate%></div>
            <div><img src="Images/<%=a.image%>" style="width: 200px; height: 300px;" /></div>
            <div style="font-size: 14px;"><%=a.text%></div>
        </div>
                
        <% } 
         ArrayList<Article> al = Article.getAll();
        
         for(Article a : al)
            out.print("<a href='index.jsp?aid="+a.id+"'>"+a.title+"</a><br />");
      
        %>
        
           
    </body>
</html>
