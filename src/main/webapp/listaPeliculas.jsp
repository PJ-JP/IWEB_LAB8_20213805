<%--
  Created by IntelliJ IDEA.
  User: Jeanpier
  Date: 07/11/2024
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.example.iweb_lab8.beans.Pelicula"%>
<%--@page import="com.example.iweb_lab8.beans.Genero"--%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Pelicula> listaPeliculas = (ArrayList<Pelicula>) request.getAttribute("lista");
    NumberFormat formatter = NumberFormat.getInstance();
%>

<%--<jsp:useBean type="java.util.ArrayList<com.example.iweb_lab8.beans.Pelicula>" scope="request" id="lista"  />%--%>

<html>
<head>
    <title>Peliculas</title>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1"--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
    <h1>Lista de películas</h1>
    <form action="<%=request.getContextPath()%>/PeliculaServlet?action=filtrar" method="POST">
        <div style="display: flex; margin-right: 5px">
            <label><input type="text" name="titulo" placeholder="Buscar..." value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>"></label>
            <button type="submit">Buscar</button>
        </div>
    </form>

    <table border="1">
        <tr>
            <th>Título</th>
            <th>Director</th>
            <th>Año Publicación</th>
            <th>Rating</th>
            <th>BoxOffice</th>
            <th>Genero</th>
            <th>Actores</th>
            <th>Borrar</th>
        </tr>
        <%
            for (Pelicula pelicula : listaPeliculas) {
        %>
        <tr>
            <td><a href="<%=request.getContextPath()%>/DetallesServlet?action=edit&id=<%= pelicula.getIdPelicula() %>"><%=pelicula.getTitulo()%></a></td>
            <td><%=pelicula.getDirector()%></td>
            <td><%=pelicula.getAnoPublicacion()%></td>
            <td><%=pelicula.getRating()%>/10</td>
            <td><%=formatter.format(pelicula.getBoxOffice())%></td>
            <td><%=pelicula.getGenero().getNombre()%></td>
            <td><a href="<%=request.getContextPath()%>/ActorServlet?action=read&id=<%= pelicula.getIdPelicula() %>">Ver Actores</a></td>
            <td> <a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/PeliculaServlet?action=del&id=<%= pelicula.getIdPelicula() %>">Borrar</a></td>
        </tr>
        <%}%>
    </table>
</body>
</html>
