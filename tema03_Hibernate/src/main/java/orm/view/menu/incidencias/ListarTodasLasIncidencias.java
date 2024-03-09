package orm.view.menu.incidencias;

import orm.model.IncidenciasDto;
import orm.service.IncidenciasService;

import java.util.List;

import static orm.utilities.Util.*;

public class ListarTodasLasIncidencias {

    private static final IncidenciasService incidenciasService = new IncidenciasService();

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
