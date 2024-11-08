package com.example.iweb_lab8.daos;

import com.example.iweb_lab8.beans.Genero;
import com.example.iweb_lab8.beans.Pelicula;
import java.sql.*;
import java.util.ArrayList;

public class PeliculaDao extends DaoBase{

    public ArrayList<Pelicula> listarPeliculas() {

        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();


        try (Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from pelicula p " +
                                 "left join genero g on p.idGenero=g.idGenero order by p.rating DESC,p.boxOffice DESC;");) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                Genero genero = new Genero();

                pelicula.setIdPelicula(rs.getInt(1));
                pelicula.setTitulo(rs.getString(2));
                pelicula.setDirector(rs.getString(3));
                pelicula.setAnoPublicacion(rs.getInt(4));
                pelicula.setRating(rs.getDouble(5));
                pelicula.setBoxOffice(rs.getDouble(6));
                genero.setIdGenero(rs.getInt(8));
                genero.setNombre(rs.getString(9));
                pelicula.setGenero(genero);
                listaPeliculas.add(pelicula);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPeliculas;
    }

    public Pelicula buscarPorId(int id){
        Pelicula pelicula = null;
        Genero genero = null;
        String sql = "select * from pelicula p left join genero g on p.idGenero=g.idGenero where p.idPelicula=?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1,id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pelicula = new Pelicula();
                    genero = new Genero();
                    pelicula.setIdPelicula(rs.getInt(1));
                    pelicula.setTitulo(rs.getString(2));
                    pelicula.setDirector(rs.getString(3));
                    pelicula.setAnoPublicacion(rs.getInt(4));
                    pelicula.setRating(rs.getDouble(5));
                    pelicula.setBoxOffice(rs.getDouble(6));
                    genero.setIdGenero(rs.getInt(8));
                    genero.setNombre(rs.getString(9));
                    pelicula.setGenero(genero);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pelicula;
    }

    public void borrar (int peliculaId) throws SQLException {

        String sql = "delete from protagonistas where idPelicula=?;";


        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
             pstmt.setInt(1,peliculaId);
             pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql="delete from pelicula where idPelicula=?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,peliculaId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizar(Pelicula pelicula){

        String sql = "update pelicula set titulo = ?, director = ?, anoPublicacion = ?, rating = ?, boxOffice = ? where idPelicula = ?";

        try (Connection conn = this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setString(1,pelicula.getTitulo());
            pstmt.setString(2,pelicula.getDirector());
            pstmt.setInt(3,pelicula.getAnoPublicacion());
            pstmt.setDouble(4,pelicula.getRating());
            pstmt.setDouble(5,pelicula.getBoxOffice());
            pstmt.setInt(6,pelicula.getIdPelicula());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Pelicula> buscarTitle(String busca){

        ArrayList<Pelicula> lista = new ArrayList<>();

        String sql = "select * from pelicula p, genero g where p.idGenero=g.idGenero and lower(p.titulo) like lower(?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setString(1,"%" +busca+ "%");

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    Genero genero = new Genero();
                    pelicula.setIdPelicula(rs.getInt(1));
                    pelicula.setTitulo(rs.getString(2));
                    pelicula.setDirector(rs.getString(3));
                    pelicula.setAnoPublicacion(rs.getInt(4));
                    pelicula.setRating(rs.getDouble(5));
                    pelicula.setBoxOffice(rs.getDouble(6));
                    genero.setIdGenero(rs.getInt(8));
                    genero.setNombre(rs.getString(9));
                    pelicula.setGenero(genero);
                    lista.add(pelicula);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
