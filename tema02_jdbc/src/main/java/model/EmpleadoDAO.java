package model;

import java.util.List;

public interface EmpleadoDAO {
    void insertar(Empleado empleado);
    void modificar(Empleado empleado);
    void cambiarContrasena(int idEmpleado, String nuevaContrasena);
    void eliminar(int idEmpleado);
    Empleado obtenerPorId(int idEmpleado);
    List<Empleado> obtenerTodos();
}

