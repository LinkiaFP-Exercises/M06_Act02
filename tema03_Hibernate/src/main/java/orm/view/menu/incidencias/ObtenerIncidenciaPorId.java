package orm.view.menu.incidencias;

import orm.model.IncidenciasDto;
import orm.service.IncidenciasService;
import orm.utilities.Util;

import static orm.utilities.Util.*;
import static orm.utilities.Util.printLnRed;

/**
 * Permite a los usuarios buscar y mostrar los detalles de una incidencia específica usando su ID.
 * Esta clase proporciona una funcionalidad crítica para el seguimiento de incidencias, permitiendo
 * a los usuarios acceder a información detallada sobre incidencias particulares para su revisión
 * o gestión adicional.
 * <p>
 * La capacidad de recuperar incidencias por ID es esencial para la administración efectiva de incidencias,
 * facilitando la identificación y el acceso rápido a la información relevante de incidencias registradas.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see IncidenciasDto
 * @see IncidenciasService
 * @see Util
 */
public class ObtenerIncidenciaPorId {

    private static final Util util = new Util();
    private static final IncidenciasService incidenciasService = new IncidenciasService();

    /**
     * Ejecuta la búsqueda de una incidencia por su ID y muestra sus detalles si se encuentra.
     * Interactúa con el usuario para obtener el ID de la incidencia y utiliza {@link IncidenciasService}
     * para buscar la incidencia en la base de datos.
     */
    public static void run() {
        try {
            printlnGreen("--- OBTENER INCIDENCIA POR ID ---");
            int idIncidencia = util.pideEntero("Introduce el ID de la incidencia: ");
            IncidenciasDto incidencia = incidenciasService.buscarIncidenciaPorId(idIncidencia);

            if (incidencia != null) {
                printlnGreen("Incidencia encontrada: ");
                printIncidencia(incidencia);
            } else {
                printLnRed("Incidencia no encontrada.");
            }
        } catch (Exception e) {
            printLnRed("Error al obtener la incidencia: " + e.getMessage());
        }
    }

}
