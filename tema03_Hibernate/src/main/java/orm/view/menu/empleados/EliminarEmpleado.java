package orm.view.menu.empleados;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;

/**
 * Facilita la interfaz para eliminar un empleado del sistema.
 * Interactúa con el usuario a través de la consola para obtener el ID del empleado a eliminar,
 * verifica que el empleado exista y procede con su eliminación.
 * <p>
 * Este proceso implica una operación crítica y debe asegurarse de que el empleado a eliminar es el correcto
 * para evitar la eliminación accidental de datos.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadosDto
 * @see EmpleadoService
 * @see Util
 */
public class EliminarEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();

    /**
     * Ejecuta el flujo para eliminar un empleado. Solicita al usuario el ID del empleado a eliminar,
     * busca al empleado para confirmar su existencia y, si se encuentra, lo elimina del sistema.
     */
    public static void run() {
        try {
            printlnGreen("--- ELIMINAR EMPLEADO ---");

            int idEmpleado = util.pideEntero("Introduce el ID del empleado a eliminar: ");
            EmpleadosDto empleado = empleadoService.buscarPorId(idEmpleado);

            if (empleado == null) {
                printLnRed("Empleado no encontrado.");
                return;
            }

            empleadoService.eliminarEmpleado(idEmpleado);
            printlnGreen("Empleado eliminado correctamente.");

        } catch (Exception e) {
            printLnRed("Error eliminando el empleado: " + e.getMessage());
        }
    }

}
