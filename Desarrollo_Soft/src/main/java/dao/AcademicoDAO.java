/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ander
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Estudio;

public class AcademicoDAO extends BaseDao {

    // Create
    public boolean create(Estudio estudio) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO academico (id_funcionario, universidad, nivel_estudio, titulo) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, estudio.getFuncionarioId());
            ps.setString(2, estudio.getUniversidad());
            ps.setString(3, estudio.getNivelEstudio());
            ps.setString(4, estudio.getTitulo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Read
    public Estudio getById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM academico WHERE id_academico = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Estudio(
                        rs.getInt("id_academico"),
                        rs.getInt("id_funcionario"),
                        rs.getString("universidad"),
                        rs.getString("nivel_estudio"),
                        rs.getString("titulo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update
    public boolean update(Estudio estudio) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE academico SET id_funcionario = ?, universidad = ?, nivel_estudio = ?, titulo = ? WHERE id_academico = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, estudio.getFuncionarioId());
            ps.setString(2, estudio.getUniversidad());
            ps.setString(3, estudio.getNivelEstudio());
            ps.setString(4, estudio.getTitulo());
            ps.setInt(5, estudio.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM academico WHERE id_academico = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // List
    public List<Estudio> listAll(int id) throws SQLException, ClassNotFoundException {
        List<Estudio> estudios = new ArrayList<>();
        String sql = "select * from academico as g WHERE g.id_funcionario = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                estudios.add(new Estudio(
                        rs.getInt("id_academico"),
                        rs.getInt("id_funcionario"),
                        rs.getString("universidad"),
                        rs.getString("nivel_estudio"),
                        rs.getString("titulo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudios;
    }
}

