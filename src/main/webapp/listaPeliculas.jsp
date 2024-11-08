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
</head>
<body>
    <h1>Lista de películas</h1>
    <form action="listaPeliculas" method="POST">
        <div class="combobox-container">
            <input type="hidden" name="action" value="filtrar">
            <button type="submit">Filtrar</button>
            <form action="listaPeliculas?action=listar" method="GET">
                <button type="submit">Limpiar</button>
            </form>
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
            <td><a href="viewPelicula?idPelicula=<%= pelicula.getIdPelicula() %>"><%=pelicula.getTitulo()%></a></td>
            <td><%=pelicula.getDirector()%></td>
            <td><%=pelicula.getAnoPublicacion()%></td>
            <td><%=pelicula.getRating()%>/10</td>
            <td><%=formatter.format(pelicula.getBoxOffice())%></td>
            <td><%=pelicula.getGenero().getNombre()%></td>
            <td><a href="listaActores?idPelicula=<%= pelicula.getIdPelicula() %>">Ver Actores</a></td>
            <td> <a href="<%=request.getContextPath()%>/PeliculaServlet?action=del&id=<%= pelicula.getIdPelicula() %>" class="button-link">Borrar</a></td>
        </tr>
        <%}%>
    </table>
</body>
</html>
