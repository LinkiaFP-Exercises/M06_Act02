package orm.view.menu.empleados;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;

import java.util.List;

import static orm.utilities.Util.*;

/**
 * Facilita la visualización de un listado completo de todos los empleados registrados en el sistema.
 * Recupera la información de cada empleado a través del {@link EmpleadoService} y la presenta al usuario
 * en formato de lista, mostrando detalles como el ID, nombre de usuario, nombre completo y teléfono de contacto
 * de cada empleado.
 * <p>
 * Esta clase es esencial para operaciones de administración y supervisión, permitiendo a los usuarios
 * obtener una visión general de los empleados existentes en el sistema.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadosDto
 * @see EmpleadoService
 */
public class ListarTodosLosEmpleados {

    private static final EmpleadoService empleadoService = new EmpleadoService();

    /**
     * Ejecuta la acción de recuperar y mostrar la lista de todos los empleados.
     * Verifica si la lista está vacía e informa al usuario o, de lo contrario, muestra los detalles
     * de cada empleado en la consola.
     */
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
