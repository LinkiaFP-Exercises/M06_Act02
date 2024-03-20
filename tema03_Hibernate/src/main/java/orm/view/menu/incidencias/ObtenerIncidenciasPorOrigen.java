package orm.view.menu.incidencias;

import orm.model.EmpleadosDto;
import orm.model.IncidenciasDto;
import orm.service.EmpleadoService;
import orm.service.IncidenciasService;
import orm.utilities.Util;

import java.util.List;

import static orm.utilities.Util.*;

/**
 * Permite a los usuarios buscar y visualizar las incidencias reportadas por un empleado específico,
 * usando su ID como criterio de búsqueda. Este proceso es importante para el seguimiento y análisis
 * de las incidencias originadas por un mismo empleado, permitiendo identificar patrones o áreas de
 * mejora dentro de la organización.
 *
 * Es una herramienta clave para la gestión de incidencias, promoviendo una mayor transparencia y
 * responsabilidad entre los empleados respecto a las incidencias reportadas.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadosDto
 * @see IncidenciasDto
 * @see EmpleadoService
 * @see IncidenciasService
 * @see Util
 */
public class ObtenerIncidenciasPorOrigen {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();
    private static final IncidenciasService incidenciasService = new IncidenciasService();

    /**
     * Realiza la búsqueda y muestra las incidencias reportadas por un empleado origen.
     * Interactúa con el usuario para obtener el ID del empleado origen y utiliza {@link IncidenciasService}
     * para recuperar y mostrar las incidencias asociadas. Proporciona detalles relevantes de cada
     * incidencia encontrada para facilitar su revisión y gestión.
     */
    public static void run() {
        try {
            printlnGreen("--- OBTENER INCIDENCIAS POR ORIGEN ---");
            int idEmpleadoOrigen = util.pideEntero("Introduce el ID del empleado origen: ");

            EmpleadosDto empleadoOrigen = empleadoService.buscarPorId(idEmpleadoOrigen);
            if (empleadoOrigen == null) {
                printLnRed("Empleado de origen no encontrado.");
                return;
            }

            List<IncidenciasDto> incidenciasPorOrigen = incidenciasService.obtenerIncidenciasPorOrigen(idEmpleadoOrigen);
            if (incidenciasPorOrigen.isEmpty()) {
                printLnRed("No hay incidencias registradas para el empleado de origen con ID: " + idEmpleadoOrigen);
            } else {
                printLnYellow("Incidencias registradas por el empleado de origen con ID: " + idEmpleadoOrigen);
                for (IncidenciasDto incidencia : incidenciasPorOrigen) {
                    printIncidencia(incidencia);
                }
            }
        } catch (Exception e) {
            printLnRed("Error al obtener las incidencias por origen: " + e.getMessage());
        }
    }

}
