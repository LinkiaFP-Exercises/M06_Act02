package service;

import config.DatabaseConnection;
import model.Empleado;
import model.EmpleadoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementa las operaciones de acceso a datos para la entidad {@link Empleado}
 * definidas en la interfaz {@link EmpleadoDAO}. Utiliza {@link config.DatabaseConnection}
 * para obtener la conexión a la base de datos y ejecutar las operaciones SQL.
 * <p>
 * Provee la implementación de los métodos para insertar, modificar, cambiar la contraseña,
 * eliminar, obtener por ID, y listar todos los empleados en la base de datos.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 */
public class EmpleadoService implements EmpleadoDAO {

    private static final Logger log = Logger.getLogger(EmpleadoService.class.getName());

    /**
     * Inserta un nuevo empleado en la base de datos.
     * Utiliza una instrucción SQL INSERT para añadir un nuevo registro de empleado con los datos proporcionados.
     *
     * @param empleado El objeto {@link Empleado} que contiene la información del empleado a insertar.
     * @return El número de filas afectadas por la operación. Retorna -1 si ocurre un error.
     */
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

    /**
     * Modifica los datos de un empleado existente en la base de datos.
     * Actualiza la información del empleado especificado por su ID con los nuevos datos proporcionados.
     *
     * @param empleado El objeto {@link Empleado} que contiene la nueva información del empleado a modificar.
     * @return El número de filas afectadas por la operación. Retorna -1 si ocurre un error.
     */
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

    /**
     * Cambia la contraseña de un empleado.
     * Actualiza la contraseña del empleado especificado por su ID con la nueva contraseña proporcionada.
     *
     * @param idEmpleado El ID del empleado cuya contraseña se va a cambiar.
     * @param nuevaContrasena La nueva contraseña para el empleado.
     * @return El número de filas afectadas por la operación. Retorna -1 si ocurre un error.
     */
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

    /**
     * Elimina un empleado de la base de datos.
     * Borra el registro del empleado especificado por su ID.
     *
     * @param idEmpleado El ID del empleado a eliminar.
     * @return El número de filas afectadas por la operación. Retorna -1 si ocurre un error.
     */
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

    /**
     * Obtiene los datos de un empleado por su ID.
     * Busca en la base de datos el empleado especificado por el ID y retorna un objeto {@link Empleado} con sus datos.
     *
     * @param idEmpleado El ID del empleado a buscar.
     * @return Un objeto {@link Empleado} con los datos del empleado encontrado o null si no se encuentra.
     */
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

    /**
     * Obtiene una lista de todos los empleados registrados en la base de datos.
     * Recupera todos los registros de empleados y los devuelve en forma de una lista de objetos {@link Empleado}.
     *
     * @return Una lista de objetos {@link Empleado} con los datos de todos los empleados. La lista puede estar vacía si no hay empleados.
     */
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
