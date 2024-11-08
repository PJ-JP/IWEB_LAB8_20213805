<%--
  Created by IntelliJ IDEA.
  User: Jeanpier
  Date: 08/11/2024
  Time: 2:27
  To change this template use File | Settings | File Templates.
--%>
<%--<%@page import="java.util.ArrayList"%>--%>
<%@page import="com.example.iweb_lab8.beans.Actor"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" type="java.util.ArrayList<com.example.iweb_lab8.beans.Actor>" scope="request" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Actores de: <%--=movie.getTitulo()--%></h1>
<table border="1">
  <tr>
    <th>idActor</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Ano Nacimiento</th>
    <th>Ganador Premio Oscar</th>

  </tr>
  <%
    for (Actor actor : lista) {
  %>
  <tr>

    <td><%=actor.getIdActor()%></td>
    <td><%=actor.getNombre()%></td>
    <td><%=actor.getApellido()%></td>
    <td><%=actor.getAnoNacimiento()%></td>
    <td><%=actor.getPremioOscar()==1 ? "true" : "false"%></td>


  </tr>

  <%
    }
  %>
</table>
</body>
</html>
