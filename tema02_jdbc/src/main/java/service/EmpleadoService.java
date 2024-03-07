package service;

import config.DatabaseConnection;
import model.Empleado;
import model.EmpleadoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EmpleadoService implements EmpleadoDAO {

    private static final Logger log = Logger.getLogger(EmpleadoService.class.getName());

    @Override
    public int insertar(Empleado empleado) {
        int affectedRows = -1;
        String sql = "INSERT INTO empleados (nombre_usuario, contrasena, nombre_completo, telefono_contacto) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.get().prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombreUsuario());
            stmt.setString(2, empleado.getContrasena());
            stmt.setString(3, empleado.getNombreCompleto());
            stmt.setString(4, empleado.getTelefonoContacto());
            affectedRows = stmt.executeUpdate();
        } catch (SQLException e) {
            log.severe("Error al insertar nuevo empleado: " + e.getMessage());
        }
        return affectedRows;
    }

    @Override
    public int modificar(Empleado empleado) {
        int affectedRows = -1;
        String sql = "UPDATE empleados SET nombre_usuario = ?, contrasena = ?, nombre_completo = ?, telefono_contacto = ? WHERE id_empleado = ?";
        try (PreparedStatement stmt = DatabaseConnection.get().prepareStatement(sql)) {

            stmt.setString(1, empleado.getNombreUsuario());
            stmt.setString(2, empleado.getContrasena());
            stmt.setString(3, empleado.getNombreCompleto());
            stmt.setString(4, empleado.getTelefonoContacto());
            stmt.setInt(5, empleado.getIdEmpleado());

            affectedRows = stmt.executeUpdate();

        } catch (SQLException e) {
            log.severe("Error al modificar el empleado: " + e.getMessage());
        }
        return affectedRows;
    }

    @Override
    public int cambiarContrasena(int idEmpleado, String nuevaContrasena) {
        int affectedRows = -1;
        String sql = "UPDATE empleados SET contrasena = ? WHERE id_empleado = ?";
        try (PreparedStatement stmt = DatabaseConnection.get().prepareStatement(sql)) {
            stmt.setString(1, nuevaContrasena);
            stmt.setInt(2, idEmpleado);
            affectedRows = stmt.executeUpdate();
        } catch (SQLException e) {
            log.severe("Error al actualizar la contraseña del empleado: " + e.getMessage());
        }
        return affectedRows;
    }

    @Override
    public int eliminar(int idEmpleado) {
        int affectedRows = -1;
        String sql = "DELETE FROM empleados WHERE id_empleado = ?";
        try (PreparedStatement stmt = DatabaseConnection.get().prepareStatement(sql)) {
            stmt.setInt(1, idEmpleado);
            affectedRows = stmt.executeUpdate();
        } catch (SQLException e) {
            log.severe("Error al eliminar el empleado: " + e.getMessage());
        }
        return affectedRows;
    }

    @Override
    public Empleado obtenerPorId(int idEmpleado) {
        Empleado empleado = null;
        String sql = "SELECT id_empleado, nombre_usuario, contrasena, nombre_completo, telefono_contacto FROM empleados WHERE id_empleado = ?";

        try (PreparedStatement stmt = DatabaseConnection.get().prepareStatement(sql)) {

            stmt.setInt(1, idEmpleado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado(
                            rs.getInt("id_empleado"),
                            rs.getString("nombre_usuario"),
                            rs.getString("contrasena"),
                            rs.getString("nombre_completo"),
                            rs.getString("telefono_contacto"));
                }
            }

        } catch (SQLException e) {
            log.severe("Error al buscar un empleado: " + e.getMessage());
        }
        return empleado;
    }

    @Override
    public List<Empleado> obtenerTodos() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT id_empleado, nombre_usuario, contrasena, nombre_completo, telefono_contacto FROM empleados";
        try (PreparedStatement stmt = DatabaseConnection.get().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre_usuario"),
                        rs.getString("contrasena"),
                        rs.getString("nombre_completo"),
                        rs.getString("telefono_contacto"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            log.severe("Error al listar todos los empleado: " + e.getMessage());
        }
        return empleados;
    }
}
