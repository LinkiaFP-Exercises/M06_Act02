package orm.view.menu.incidencias;

import orm.model.EmpleadosDto;
import orm.model.IncidenciasDto;
import orm.service.EmpleadoService;
import orm.service.IncidenciasService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;

/**
 * Facilita la creación de una nueva incidencia en el sistema.
 * Solicita al usuario información clave sobre la incidencia, como los empleados involucrados (origen y destino),
 * el detalle de la incidencia, y el tipo. Esta información se utiliza para crear un registro de incidencia
 * que se añade a la base de datos a través del {@link IncidenciasService}.
 * <p>
 * Este proceso asegura que se recolecten todos los datos necesarios para una adecuada gestión y seguimiento
 * de las incidencias dentro de la organización.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadosDto
 * @see IncidenciasDto
 * @see EmpleadoService
 * @see IncidenciasService
 * @see Util
 */
public class CrearNuevaIncidencia {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();
    private static final IncidenciasService incidenciasService = new IncidenciasService();

    /**
     * Ejecuta la lógica para recoger datos del usuario y registrar una nueva incidencia en el sistema.
     * Valida la existencia de los empleados involucrados y recopila la información detallada de la incidencia
     * antes de proceder con su creación.
     */
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
