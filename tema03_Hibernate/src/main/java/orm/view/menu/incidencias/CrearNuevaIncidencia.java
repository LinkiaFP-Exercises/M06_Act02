package orm.view.menu.incidencias;

import orm.model.EmpleadosDto;
import orm.model.IncidenciasDto;
import orm.service.EmpleadoService;
import orm.service.IncidenciasService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;

public class CrearNuevaIncidencia {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();
    private static final IncidenciasService incidenciasService = new IncidenciasService();
    public static void run() {
        try {
            printlnGreen("--- CREAR NUEVA INCIDENCIA ---");
            String nombreUsuarioOrigen = util.pideTexto("Introduce el nombre de usuario del empleado origen: ");
            EmpleadosDto empleadoOrigen = empleadoService.buscarPorUsuario(nombreUsuarioOrigen);
            if (empleadoOrigen == null) {
                printLnRed("Empleado de origen no encontrado.");
                return;
            }

            String nombreUsuarioDestino = util.pideTexto("Introduce el nombre de usuario del empleado destino: ");
            EmpleadosDto empleadoDestino = empleadoService.buscarPorUsuario(nombreUsuarioDestino);
            if (empleadoDestino == null) {
                printLnRed("Empleado de destino no encontrado.");
                return;
            }

            String detalle = util.pideTexto("Introduce el detalle de la incidencia: ");
            String tipo = util.pideTexto("Introduce el tipo de la incidencia (N/U): ");

            IncidenciasDto nuevaIncidencia = new IncidenciasDto();
            nuevaIncidencia.setEmpleadosByIdEmpleadoOrigen(empleadoOrigen);
            nuevaIncidencia.setEmpleadosByIdEmpleadoDestino(empleadoDestino);
            nuevaIncidencia.setDetalle(detalle);
            nuevaIncidencia.setTipo(tipo);
            nuevaIncidencia.setFechaHora(new java.sql.Timestamp(new java.util.Date().getTime()));

            incidenciasService.crearIncidencia(nuevaIncidencia);
            printlnGreen("Incidencia creada correctamente.");
        } catch (Exception e) {
            printLnRed("Error al crear la incidencia: " + e.getMessage());
        }
    }

}
