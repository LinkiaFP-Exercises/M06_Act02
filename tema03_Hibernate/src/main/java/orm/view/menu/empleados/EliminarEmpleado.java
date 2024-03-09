package orm.view.menu.empleados;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;

public class EliminarEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();

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
