package com.example.iweb_lab8.servlets;


import java.io.*;
import java.util.ArrayList;

import com.example.iweb_lab8.beans.Pelicula;
import com.example.iweb_lab8.beans.Actor;
import com.example.iweb_lab8.daos.ActorDao;
import com.example.iweb_lab8.daos.PeliculaDao;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

@WebServlet(name = "ActorServlet", value = "/ActorServlet")
public class ActorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        PeliculaDao peliculaDao = new PeliculaDao();
        ActorDao actorDao = new ActorDao();

        switch (action) {
            case "read":
                String id = request.getParameter("id");
                ArrayList<Actor> list = actorDao.listarActores(Integer.parseInt(id));
                Pelicula pelicula = peliculaDao.buscarPorId(Integer.parseInt(id));
                if(list != null){
                    request.setAttribute("lista",list);
                    request.setAttribute("pelicula",pelicula);
                    request.getRequestDispatcher("listaActores.jsp").forward(request,response);

                }else{
                    response.sendRedirect(request.getContextPath() + "/PeliculasServlet");/*DetallesServlet*/
                }
                break;
            case "create":
                request.setAttribute("pelicula", peliculaDao.buscarPorId(Integer.parseInt(request.getParameter("id"))));
                request.getRequestDispatcher("crearActor.jsp").forward(request,response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        ActorDao actorDao = new ActorDao();

        switch (action) {

            case "create":
                int idPelicula = Integer.parseInt(request.getParameter("id"));

                int idActor = actorDao.ultimoID()+1;
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                int anoNacimiento = Integer.parseInt(request.getParameter("anoNacimiento"));
                int premioOscar = Integer.parseInt(request.getParameter("premioOscar"));

                Actor actor = new Actor();
                actor.setIdActor(idActor);
                actor.setNombre(nombre);
                actor.setApellido(apellido);
                actor.setAnoNacimiento(anoNacimiento);
                actor.setPremioOscar(premioOscar);

                actorDao.crearActor(actor,idPelicula);
                response.sendRedirect(request.getContextPath()+"/ActorServlet?action=read&id="+idPelicula);
                break;
        }
    }
}