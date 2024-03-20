package orm.view.menu.empleados;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;

/**
 * Maneja el proceso de cambio de contraseña para los empleados.
 * Interactúa con el usuario para realizar el cambio de contraseña de un empleado específico,
 * validando la contraseña antigua antes de establecer una nueva.
 * <p>
 * Este proceso incluye la búsqueda del empleado por ID o nombre de usuario,
 * la verificación de la contraseña actual, y la actualización a la nueva contraseña en la base de datos.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadosDto
 * @see EmpleadoService
 * @see Util
 */
public class CambiarContrasenaEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();

    /**
     * Ejecuta el flujo para cambiar la contraseña de un empleado.
     * Solicita al usuario la identificación del empleado y las contraseñas (antigua y nueva),
     * y realiza la actualización si la información es válida.
     */
    public static void run() {
        try {
            printlnGreen("--- CAMBIAR CONTRASEÑA DE EMPLEADO ---");

            EmpleadosDto empleado = buscarEmpleado();

            if (empleado == null) {
                printLnRed("Empleado no encontrado.");
                return;
            }

            String antiguaContrasena = util.pideTexto("Introduce la contraseña antigua: ");
            String nuevaContrasena = util.pideTexto("Introduce la nueva contraseña: ");

            if (!empleado.getContrasena().equals(antiguaContrasena)) {
                printLnRed("La contraseña antigua no coincide.");
                return;
            }

            empleado.setContrasena(nuevaContrasena);
            boolean exito = empleadoService.cambiarContrasenaEmpleadoTrusted(empleado);

            if (exito) {
                printlnGreen("Contraseña cambiada correctamente.");
            } else {
                printLnRed("Error al cambiar la contraseña.");
            }
        } catch (Exception e) {
            printLnRed("Error al cambiar la contraseña: " + e.getMessage());
        }
    }

    /**
     * Busca un empleado por ID o nombre de usuario, según la elección del usuario.
     *
     * @return El {@link EmpleadosDto} del empleado encontrado o null si no se encuentra.
     */
    static EmpleadosDto buscarEmpleado() {
        int opcionBusqueda = util.pideEntero("Buscar por (1) ID o (2) Nombre de Usuario: ");
        EmpleadosDto empleado = null;

        if (opcionBusqueda == 1) {
            int idEmpleado = util.pideEntero("Introduce el ID del empleado: ");
            empleado = empleadoService.buscarPorId(idEmpleado);
        } else if (opcionBusqueda == 2) {
            String nombreUsuario = util.pideTexto("Introduce el nombre de usuario del empleado: ");
            empleado = empleadoService.buscarPorUsuario(nombreUsuario);
        } else {
            printLnRed("Opción no válida.");
        }

        if (empleado == null) {
            printLnRed("Empleado no encontrado.");
        }

        return empleado;
    }
}
