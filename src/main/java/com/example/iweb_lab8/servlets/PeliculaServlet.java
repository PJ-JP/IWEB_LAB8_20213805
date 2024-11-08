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

@WebServlet(name = "PeliculaServlet", value = "/PeliculaServlet")
public class PeliculaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        PeliculaDao peliculaDao = new PeliculaDao();

        switch (action) {
            case "lista":
                ArrayList<Pelicula> list = peliculaDao.listarPeliculas();
                request.setAttribute("lista", list);
                RequestDispatcher rd = request.getRequestDispatcher("listaPeliculas.jsp");
                rd.forward(request, response);
                break;
            case "del":
                String idd = request.getParameter("id");
                int id=Integer.parseInt(idd);
                Pelicula pell = peliculaDao.buscarPorId(id);

                if(pell != null){
                    try {
                        peliculaDao.borrar(id);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/PeliculaServlet");
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        /*String action = request.getParameter("action");
        PeliculaDao peliculaDao = new PeliculaDao();
        //listasDao listaDao = new listasDao();
        //ArrayList<genero> listaGeneros = listaDao.listarGeneros();
        //ArrayList<streaming> listaStreaming = listaDao.listarStraming();

        switch (action) {


            case "filtrar":

                RequestDispatcher viewFiltro = request.getRequestDispatcher("listaPeliculas.jsp");
                viewFiltro.forward(request,response);
                break;

            case "editar":
                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                String titulo = request.getParameter("titulo");
                String director = request.getParameter("director");
                int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
                double rating = Double.parseDouble(request.getParameter("rating"));
                double boxOffice = Double.parseDouble(request.getParameter("boxOffice"));
                String genero = request.getParameter("genero");
                peliculaDao.editarPelicula(idPelicula, titulo,director,anoPublicacion,rating,boxOffice);
                response.sendRedirect(request.getContextPath()+"/listaPeliculas?action=listar");
                break;
        }
        */
    }
}
