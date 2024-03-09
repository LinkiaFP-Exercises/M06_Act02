package orm.view.menu.incidencias;

import orm.model.EmpleadosDto;
import orm.model.IncidenciasDto;
import orm.service.EmpleadoService;
import orm.service.IncidenciasService;
import orm.utilities.Util;

import java.util.List;

import static orm.utilities.Util.*;

public class ObtenerIncidenciasPorDestino {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();
    private static final IncidenciasService incidenciasService = new IncidenciasService();

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
