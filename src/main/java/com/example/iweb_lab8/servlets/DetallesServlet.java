package com.example.iweb_lab8.servlets;


/*import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;*/

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.iweb_lab8.beans.Pelicula;
import com.example.iweb_lab8.daos.PeliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

@WebServlet(name = "DetallesServlet", value = "/DetallesServlet")
public class DetallesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        PeliculaDao peliculaDao = new PeliculaDao();

        switch (action) {
            case "edit":
                String id = request.getParameter("id");
                Pelicula pelicula = peliculaDao.buscarPorId(Integer.parseInt(id));

                if(pelicula != null){
                    request.setAttribute("pelicula",pelicula);
                    request.getRequestDispatcher("viewPelicula.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/PeliculasServlet");/*DetallesServlet*/
                }
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        PeliculaDao peliculaDao = new PeliculaDao();

        //listasDao listaDao = new listasDao();
        //ArrayList<genero> listaGeneros = listaDao.listarGeneros();
        //ArrayList<streaming> listaStreaming = listaDao.listarStraming();

        switch (action) {


            /*case "filtrar":

                RequestDispatcher viewFiltro = request.getRequestDispatcher("listaPeliculas.jsp");
                viewFiltro.forward(request,response);
                break;*/

            case "edit":
                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                String titulo = request.getParameter("titulo");
                String director = request.getParameter("director");
                int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
                double rating = Double.parseDouble(request.getParameter("rating"));
                double boxOffice = Double.parseDouble(request.getParameter("boxOffice"));

                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(idPelicula);
                pelicula.setTitulo(titulo);
                pelicula.setDirector(director);
                pelicula.setAnoPublicacion(anoPublicacion);
                pelicula.setRating(rating);
                pelicula.setBoxOffice(boxOffice);

                peliculaDao.actualizar(pelicula);
                response.sendRedirect(request.getContextPath()+"/PeliculaServlet?action=lista");
                break;
        }
    }
}