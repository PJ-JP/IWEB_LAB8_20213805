<%--
  Created by IntelliJ IDEA.
  User: Jeanpier
  Date: 07/11/2024
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="pelicula" type="com.example.iweb_lab8.beans.Pelicula" scope="request" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Pelicula Numero <%=pelicula.getIdPelicula()%></h1>

<table border="1">
    <form method="POST" action="<%=request.getContextPath()%>/DetallesServlet?action=edit">
        <input type="hidden" name="idPelicula" value="<%=pelicula.getIdPelicula()%>">
        <tr>
            <th>Titulo</th>
            <td contenteditable>
                <input name="titulo" value="<%=pelicula.getTitulo()%>">
            </td>
        </tr>
        <tr>
            <th>Director</th>
            <td contenteditable>
                <input name="director" value="<%=pelicula.getDirector()%>">
            </td>
        </tr>
        <tr>
            <th>Año Publicacion</th>
            <td contenteditable>
                <input name="anoPublicacion" value="<%=pelicula.getAnoPublicacion()%>">
            </td>
        </tr>
        <tr>
            <th>Rating</th>
            <td contenteditable>
                <input name="rating" value="<%=pelicula.getRating()%>">
            </td>
        </tr>
        <tr>
            <th>BoxOffice</th>
            <td contenteditable>
                <input name="boxOffice" value="<%=pelicula.getBoxOffice()%>">
            </td>
        </tr>
        <%--<tr>
            <th>Genero</th>
            <td contenteditable>
                <input name="Genero" value="<%=pelicula.getGenero()%>">
            </td>
        </tr>--%>
        <tr>
            <th>Actores</th>
            <td><a href="<%=request.getContextPath()%>/ActorServlet?action=read&id=<%=pelicula.getIdPelicula() %>">Ver Actores</a></td>
        </tr>
        <input type="hidden" name="action" value="editar">
        <button type="submit">Editar Pelicula</button>
    </form>
</table>
</body>
</html>
