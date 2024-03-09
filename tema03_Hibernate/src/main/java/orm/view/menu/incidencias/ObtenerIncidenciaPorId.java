package orm.view.menu.incidencias;

import orm.model.IncidenciasDto;
import orm.service.IncidenciasService;
import orm.utilities.Util;

import static orm.utilities.Util.*;
import static orm.utilities.Util.printLnRed;

public class ObtenerIncidenciaPorId {

    private static final Util util = new Util();
    private static final IncidenciasService incidenciasService = new IncidenciasService();

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
