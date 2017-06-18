<%-- 
    Document   : articles
    Created on : 27-Mar-2017, 20:45:04
    Author     : nikica1
--%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%> 
<%@page import="bussiness.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bussiness.Article"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if(session.getAttribute("uid")== null)
    response.sendRedirect("login.jsp");
   
   if(ServletFileUpload.isMultipartContent(request)){
       
       FileItemFactory factory = new DiskFileItemFactory();
       ServletFileUpload upload = new ServletFileUpload(factory);
       
       List items = upload.parseRequest(request);
       Iterator itr = items.iterator();
       
       String aTitle = "";
       String aImage = "";
       String aText = "";
       
       
       while(itr.hasNext()){
           FileItem item = (FileItem) itr.next();
           
           if(item.isFormField()){
               if (item.getFieldName().equals("art_title"))
                   aTitle = item.getString();
                   
               if(item.getFieldName().equals("art_text"))
                   aText = item.getString();
                  
           }
          
           if(!item.isFormField()){
               String fileName = item.getName();
               int index = fileName.lastIndexOf("\\");
               aImage = fileName.substring(index+1);
               
               String path = "C://Users/nikica1/Documents/NetBeansProjects/WebAplikacijaArticles/web/Images";
               item.write(new File(path+File.separator+aImage));
                     
           }
       }
       
       Article a = new Article(aTitle, aImage, aText, (new Date()).toString(), Integer.parseInt(session.getAttribute("uid").toString()));
       a.insert();  
   }
   %>
   
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
        <%
        ArrayList<Article> al = Article.getAll();
        
        for(Article a : al){
         %>
         
         <div style="border: 1px solid black; padding: 5px;">
             Title: <a href="index.jsp?aid=<%=a.id%>"><%=a.title%></a><br/>
             Author: <%=User.getById(a.author).nick%><br/>
             <img src="Images/<%=a.image%>" style="width: 60px; height: 60px;" />    
         </div>
         
         <%
           } 
         %>
         
         <form action="articles.jsp" method="post" enctype="multipart/form-data">
             <table border="1">
                 <tr bgcolor="#d3d3d3"><td>Title</td><td>Text</td><td></td></tr>
                 <tr>
                     <td><input type="text" name="art_title" /></td>
                     <td><textarea type="text" name="art_text"></textarea></td>
                     <td></td>        
                 </tr>
                 <tr bgcolor="#d3d3d3">
                     <td>Image</td><td></td><td></td>   
                 </tr>
                 <tr>
                     <td><input type="file" name="art_image" /></td>
                     <td></td>
                     <td></td>
                 </tr>
                 <tr>
                     <td></td><td></td>
                     <td><input type="submit" name="art_confirm" value="Confirm" /></td>
                 </tr>
             </table>
         </form>
         
    </body>
</html>
