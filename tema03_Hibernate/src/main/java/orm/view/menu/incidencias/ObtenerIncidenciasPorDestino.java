package orm.view.menu.incidencias;

import orm.model.EmpleadosDto;
import orm.model.IncidenciasDto;
import orm.service.EmpleadoService;
import orm.service.IncidenciasService;
import orm.utilities.Util;

import java.util.List;

import static orm.utilities.Util.*;

/**
 * Permite la recuperación y visualización de todas las incidencias dirigidas a un empleado específico.
 * Esta clase facilita la identificación y el seguimiento de incidencias que han sido reportadas con
 * un empleado como destino, proporcionando una herramienta útil para la revisión de las acciones
 * o medidas necesarias en respuesta a dichas incidencias.
 * <p>
 * La funcionalidad es esencial para mantener una comunicación efectiva y la gestión de incidencias
 * dentro de una organización, asegurando que los empleados y los gerentes estén informados sobre las
 * incidencias relevantes.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadosDto
 * @see IncidenciasDto
 * @see EmpleadoService
 * @see IncidenciasService
 * @see Util
 */
public class ObtenerIncidenciasPorDestino {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();
    private static final IncidenciasService incidenciasService = new IncidenciasService();

    /**
     * Ejecuta la búsqueda de incidencias destinadas a un empleado específico y muestra sus detalles.
     * Solicita al usuario el ID del empleado destino y utiliza {@link IncidenciasService} para buscar
     * y listar las incidencias. Muestra un resumen de cada incidencia encontrada.
     */
    public static void run() {
        try {
            printlnGreen("--- OBTENER INCIDENCIAS POR DESTINO ---");
            int idEmpleadoDestino = util.pideEntero("Introduce el ID del empleado destino: ");

            EmpleadosDto empleadoDestino = empleadoService.buscarPorId(idEmpleadoDestino);
            if (empleadoDestino == null) {
                printLnRed("Empleado destino no encontrado.");
                return;
            }

            List<IncidenciasDto> incidenciasPorDestino = incidenciasService.obtenerIncidenciasPorDestino(idEmpleadoDestino);
            if (incidenciasPorDestino.isEmpty()) {
                printLnRed("No hay incidencias registradas para el empleado destino con ID: " + idEmpleadoDestino);
            } else {
                printLnYellow("Incidencias dirigidas al empleado con ID: " + idEmpleadoDestino);
                for (IncidenciasDto incidencia : incidenciasPorDestino) {
                    printIncidencia(incidencia);
                }
            }
        } catch (Exception e) {
            printLnRed("Error al obtener las incidencias por destino: " + e.getMessage());
        }
    }

}
