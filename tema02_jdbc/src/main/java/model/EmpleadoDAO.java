package model;

import java.util.List;

public interface EmpleadoDAO {
    int insertar(Empleado empleado);
    int modificar(Empleado empleado);
    int cambiarContrasena(int idEmpleado, String nuevaContrasena);
    int eliminar(int idEmpleado);
    Empleado obtenerPorId(int idEmpleado);
    List<Empleado> obtenerTodos();
}

