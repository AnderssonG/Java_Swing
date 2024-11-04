/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ander
 */
import static dao.ConexionDB.getConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Funcionario;

public class FuncionarioDAO extends BaseDao {

    // Create
    public boolean create(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Funcionario (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getTipoIdentificacion());
            ps.setString(2, funcionario.getNumeroIdentificacion());
            ps.setString(3, funcionario.getNombres());
            ps.setString(4, funcionario.getApellidos());
            ps.setString(5, funcionario.getEstadoCivil());
            ps.setString(6, funcionario.getSexo());
            ps.setString(7, funcionario.getDireccion());
            ps.setString(8, funcionario.getTelefono());
            ps.setString(9, funcionario.getFechaNacimiento());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Read
    public Funcionario getByDoc(String doc) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Funcionario WHERE numero_identificacion = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Funcionario(
                        rs.getInt("id_funcionario"),
                        rs.getString("tipo_identificacion"),
                        rs.getString("numero_identificacion"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("estado_civil"),
                        rs.getString("sexo"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("fecha_nacimiento")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update
    public boolean update(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Funcionario SET tipo_identificacion = ?, numero_identificacion = ?, nombres = ?, apellidos = ?, estado_civil = ?, sexo = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE id_funcionario = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getTipoIdentificacion());
            ps.setString(2, funcionario.getNumeroIdentificacion());
            ps.setString(3, funcionario.getNombres());
            ps.setString(4, funcionario.getApellidos());
            ps.setString(5, funcionario.getEstadoCivil());
            ps.setString(6, funcionario.getSexo());
            ps.setString(7, funcionario.getDireccion());
            ps.setString(8, funcionario.getTelefono());
            ps.setString(9, funcionario.getFechaNacimiento());
            ps.setInt(10, funcionario.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Funcionario WHERE id_funcionario = ?";
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
    public List<Funcionario> listAll() throws SQLException, ClassNotFoundException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM Funcionario";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                funcionarios.add(new Funcionario(
                        rs.getInt("id_funcionario"),
                        rs.getString("tipo_identificacion"),
                        rs.getString("numero_identificacion"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("estado_civil"),
                        rs.getString("sexo"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("fecha_nacimiento")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }
}

