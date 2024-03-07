package service;

import config.DatabaseConnection;
import model.Empleado;
import model.EmpleadoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoService implements EmpleadoDAO {

    @Override
    public void insertar(Empleado empleado) {
        String sql = "INSERT INTO empleados (nombre_usuario, contrasena, nombre_completo, telefono_contacto) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombreUsuario());
            stmt.setString(2, empleado.getContrasena());
            stmt.setString(3, empleado.getNombreCompleto());
            stmt.setString(4, empleado.getTelefonoContacto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(Empleado empleado) {

    }

    @Override
    public void cambiarContrasena(int idEmpleado, String nuevaContrasena) {

    }

    @Override
    public void eliminar(int idEmpleado) {

    }

    @Override
    public Empleado obtenerPorId(int idEmpleado) {
        return null;
    }

    @Override
    public List<Empleado> obtenerTodos() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT id_empleado, nombre_usuario, contrasena, nombre_completo, telefono_contacto FROM empleados";
        try (Connection conn = DatabaseConnection.get();
             PreparedStatement stmt = conn.prepareStatement(sql);
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
            e.printStackTrace();
        }
        return empleados;
    }
}
