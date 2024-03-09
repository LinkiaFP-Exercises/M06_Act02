package orm.view.menu.incidencias;

import orm.model.EmpleadosDto;
import orm.model.IncidenciasDto;
import orm.service.EmpleadoService;
import orm.service.IncidenciasService;
import orm.utilities.Util;

import java.util.List;

import static orm.utilities.Util.*;

public class ObtenerIncidenciasPorOrigen {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();
    private static final IncidenciasService incidenciasService = new IncidenciasService();

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
