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
import models.GrupoFamiliar;

public class FamilyDao extends BaseDao {

    // Create
    public boolean create(GrupoFamiliar grupoFamiliar) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO grupo_familiar (id_funcionario, nombre, rol,fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, grupoFamiliar.getFuncionarioId());
            ps.setString(2, grupoFamiliar.getNombreFamiliar());
            ps.setString(3, grupoFamiliar.getRol());
            ps.setString(4, grupoFamiliar.getFechaNacimiento());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Read
    public GrupoFamiliar getById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM grupo_familiar WHERE id_familiar = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new GrupoFamiliar(
                        rs.getInt("id_familiar"),
                        rs.getInt("id_funcionario"),
                        rs.getString("nombre"),
                        rs.getString("rol"),
                        rs.getString("fecha_nacimiento")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update
    public boolean update(GrupoFamiliar grupoFamiliar) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE grupo_familiar SET id_funcionario = ?, nombre = ?, rol = ? ,fecha_nacimiento = ? WHERE id_familiar = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, grupoFamiliar.getFuncionarioId());
            ps.setString(2, grupoFamiliar.getNombreFamiliar());
            ps.setString(3, grupoFamiliar.getRol());
            ps.setDate(4,Date.valueOf(grupoFamiliar.getFechaNacimiento()));
            ps.setInt(5, grupoFamiliar.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM grupo_familiar WHERE id_familiar = ?";
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
    public List<GrupoFamiliar> listAll(int id) throws SQLException, ClassNotFoundException {
        List<GrupoFamiliar> familiares = new ArrayList<>();
        String sql = "select * from grupo_familiar as g WHERE g.id_funcionario = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
             ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                familiares.add(new GrupoFamiliar(
                        rs.getInt("id_familiar"),
                        rs.getInt("id_funcionario"),
                        rs.getString("nombre"),
                        rs.getString("rol"),
                        rs.getString("fecha_nacimiento")
                ));
            }
        } 
    catch (SQLException e) {
            e.printStackTrace();
        }
        return familiares;
    }
}
