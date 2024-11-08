<%--
  Created by IntelliJ IDEA.
  User: Jeanpier
  Date: 08/11/2024
  Time: 4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="pelicula" type="com.example.iweb_lab8.beans.Pelicula" scope="request" />

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
<form method="POST" action="<%=request.getContextPath()%>/ActorServlet?action=create">
  <input type="hidden" name="id" value="<%=pelicula.getIdPelicula()%>">
  <tr>
    <th>Nombre</th>
    <td contenteditable>
      <input type="text" name="nombre">
    </td>
  </tr>
  <tr>
    <th>Apellido</th>
    <td contenteditable>
      <input name="apellido" type="text">
    </td>
  </tr>
  <tr>
    <th>Año Nacimiento</th>
    <td contenteditable>
      <input name="anoNacimiento" type="text">
    </td>
  </tr>
  <tr>
    <th>Premio Oscar</th>
    <td contenteditable>
      <input name="premioOscar" type="text">
    </td>
  </tr>
  <button type="submit">Guardar</button>
</form>
</table>
</body>
</html>
