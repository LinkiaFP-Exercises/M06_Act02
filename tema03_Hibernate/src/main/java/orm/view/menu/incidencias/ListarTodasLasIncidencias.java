package orm.view.menu.incidencias;

import orm.model.IncidenciasDto;
import orm.service.IncidenciasService;

import java.util.List;

import static orm.utilities.Util.*;

/**
 * Facilita la visualización de un listado completo de todas las incidencias registradas en el sistema.
 * Recupera y muestra información detallada de cada incidencia, incluyendo el origen, destino, fecha y hora,
 * detalle, y tipo, proporcionando así una herramienta útil para la revisión y el seguimiento de incidencias.
 * <p>
 * Esta clase es esencial para la administración de incidencias, ofreciendo una forma rápida y eficiente
 * de acceder a un resumen de todas las incidencias, lo que facilita su gestión y resolución.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see IncidenciasDto
 * @see IncidenciasService
 */
public class ListarTodasLasIncidencias {

    private static final IncidenciasService incidenciasService = new IncidenciasService();

    /**
     * Ejecuta la acción de recuperar y mostrar la lista de todas las incidencias registradas.
     * Verifica si la lista está vacía e informa al usuario o, de lo contrario, muestra los detalles
     * de cada incidencia en consola.
     */
    public static void run() {
        printlnGreen("--- LISTADO DE INCIDENCIAS ---");
        List<IncidenciasDto> incidencias = incidenciasService.obtenerTodasLasIncidencias();
        if (incidencias.isEmpty()) {
            printLnRed("No hay incidencias registradas.");
        } else {
            for (IncidenciasDto incidencia : incidencias) {
                printIncidencia(incidencia);
            }
        }
    }

}
