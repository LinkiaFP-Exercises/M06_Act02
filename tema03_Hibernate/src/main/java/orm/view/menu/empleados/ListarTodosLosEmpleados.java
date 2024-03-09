package orm.view.menu.empleados;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;

import java.util.List;

import static orm.utilities.Util.*;

public class ListarTodosLosEmpleados {

    private static final EmpleadoService empleadoService = new EmpleadoService();

    public static void run() {
        printlnGreen("--- LISTADO DE EMPLEADOS ---");
        List<EmpleadosDto> empleados = empleadoService.obtenerTodosLosEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (EmpleadosDto empleado : empleados) {
                printLnYellow(printEmpleado(empleado));
            }
        }
    }

}
