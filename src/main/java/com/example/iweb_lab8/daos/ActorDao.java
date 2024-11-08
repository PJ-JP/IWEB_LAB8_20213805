package com.example.iweb_lab8.daos;

import com.example.iweb_lab8.beans.Actor;

import java.sql.*;
import java.util.ArrayList;

public class ActorDao extends DaoBase {

    public ArrayList<Actor> listarActores(int id) {

        ArrayList<Actor> listaActores = new ArrayList<>();

        String sql="select p.idActor,a.Nombre,a.Apellido,a.anoNacimiento,a.premioOscar from actor a, protagonistas p where a.idActor=p.idActor AND p.idPelicula=?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Actor actor= new Actor();
                    actor.setIdActor(rs.getInt(1));
                    actor.setNombre(rs.getString(2));
                    actor.setApellido(rs.getString(3));
                    actor.setAnoNacimiento(rs.getInt(4));
                    actor.setPremioOscar(rs.getInt(5));
                    listaActores.add(actor);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaActores;
    }

    public void crearActor(Actor actor, int id) {
        String sql = "insert into actor (idActor,Nombre,Apellido,anoNacimiento,premioOscar) values (?,?,?,?,?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, actor.getIdActor());
            pstmt.setString(2, actor.getNombre());
            pstmt.setString(3, actor.getApellido());
            pstmt.setInt(4, actor.getAnoNacimiento());
            pstmt.setInt(5, actor.getPremioOscar());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql="insert into protagonistas (idPelicula,idActor) values (?,?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, id);
            pstmt.setInt(2, actor.getIdActor());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public int ultimoID() {
        int id = 0;
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select idActor from actor order by idActor DESC;");) {
            while (rs.next()) {
                id = rs.getInt(1);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}