package orm.service;

import orm.model.EmpleadosDto;

import java.util.List;

/**
 * Servicio que ofrece operaciones para gestionar empleados.
 * Proporciona funcionalidades para insertar, buscar, validar, modificar, cambiar contraseña y eliminar empleados.
 * Estas operaciones se apoyan en {@link EmpleadoDao} para interactuar con la base de datos.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadosDto
 * @see EmpleadoDao
 */
public class EmpleadoService {
    private final EmpleadoDao empleadoDao = new EmpleadoDao();

    public void insertarEmpleado(EmpleadosDto empleado) {
        empleadoDao.guardarEmpleado(empleado);
    }
    public EmpleadosDto buscarPorId(int idEmpleado) {
        return empleadoDao.buscarEmpleadoPorId(idEmpleado);
    }
    public EmpleadosDto buscarPorUsuario(String nombreUsuario) {
        return empleadoDao.buscarEmpleadoPorNombreUsuario(nombreUsuario);
    }

    public boolean validarEmpleado(String usuario, String contrasena) {
        EmpleadosDto empleado = empleadoDao.buscarEmpleadoPorCredenciales(usuario, contrasena);
        return empleado != null;
    }

    public boolean modificarPerfilEmpleado(EmpleadosDto empleado) {
        return empleadoDao.actualizarEmpleado(empleado);
    }

    public void cambiarContrasenaEmpleado(int idEmpleado, String antiguaContrasena, String nuevaContrasena) {
        EmpleadosDto empleado = empleadoDao.buscarEmpleadoPorId(idEmpleado);
        if (empleado != null && empleado.getContrasena().equals(antiguaContrasena)) {
            empleado.setContrasena(nuevaContrasena);
            empleadoDao.actualizarContrasena(empleado);
        }
    }
    public void cambiarContrasenaEmpleado(String nombreUsuario, String antiguaContrasena, String nuevaContrasena) {
        EmpleadosDto empleado = empleadoDao.buscarEmpleadoPorNombreUsuario(nombreUsuario);
        if (empleado != null && empleado.getContrasena().equals(antiguaContrasena)) {
            empleado.setContrasena(nuevaContrasena);
            empleadoDao.actualizarContrasena(empleado);
        }
    }
    public boolean cambiarContrasenaEmpleadoTrusted(EmpleadosDto empleado) {
           return empleadoDao.actualizarContrasenaTrusted(empleado);
    }

    public void eliminarEmpleado(int idEmpleado) {
        EmpleadosDto empleado = empleadoDao.buscarEmpleadoPorId(idEmpleado);
        if (empleado != null) {
            empleadoDao.eliminarEmpleado(empleado);
        }
    }

    public List<EmpleadosDto> obtenerTodosLosEmpleados() {
        return empleadoDao.listarEmpleados();
    }
}
