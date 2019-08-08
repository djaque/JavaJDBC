<%-- 
    Document   : users
    Created on : 06-ago-2019, 23:41:07
    Author     : dany
--%>

<%@page import="java.util.List"%>
<%@page import="bean.UserBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Usuarios!</h1>
        
        <h2>Crear Usuario</h2>
        <form action="user" method="post">
            <p>
                <input type="text" name="name" placeholder="Nombre">
                <input type="text" name="email" placeholder="Email">
                <input type="password" name="password" placeholder="Password">
                <input type="submit" value="Crear">
            </p>
        </form>
        <hr>
        <h2>Lista de usuarios</h2>
        <ul>
            <% 
                List<UserBO> lu = (List<UserBO>)request.getAttribute("users");
                
                if (null == lu) {
                    out.println("<li>SIN USUARIOS</li>");
                } else {
                    for(UserBO u : lu) {
                        out.println("<li>"+u.getName()+" ("+u.getEmail()+")</li>");
                    }
                }
            %>
        </ul>
    </body>
</html>
