package model;

import java.util.List;

/**
 * Define las operaciones de acceso a datos para los objetos {@link Empleado}.
 * Proporciona métodos para insertar, modificar, cambiar la contraseña, eliminar,
 * obtener por ID, y listar todos los empleados.
 * <p>
 * Esta interfaz es un componente clave en el patrón DAO (Data Access Object),
 * permitiendo la abstracción de las operaciones CRUD (Crear, Leer, Actualizar, y Eliminar)
 * específicas para la entidad {@code Empleado}.
 *
 * @see Empleado
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 */
public interface EmpleadoDAO {
    int insertar(Empleado empleado);
    int modificar(Empleado empleado);
    int cambiarContrasena(int idEmpleado, String nuevaContrasena);
    int eliminar(int idEmpleado);
    Empleado obtenerPorId(int idEmpleado);
    List<Empleado> obtenerTodos();
}

