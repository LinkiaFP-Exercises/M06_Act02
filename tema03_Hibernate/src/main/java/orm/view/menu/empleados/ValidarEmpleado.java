package orm.view.menu.empleados;

import orm.service.EmpleadoService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;

/**
 * Permite la validación de las credenciales de un empleado.
 * Esta clase es responsable de solicitar al usuario el nombre de usuario y la contraseña,
 * y luego utilizar el {@link EmpleadoService} para verificar si coinciden con un empleado existente
 * en la base de datos.
 * <p>
 * Es una operación esencial de seguridad que asegura que solo los usuarios autenticados puedan acceder
 * a funciones restringidas dentro de la aplicación.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadoService
 * @see Util
 */
public class ValidarEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();

    /**
     * Ejecuta la validación del empleado mediante sus credenciales.
     * Interactúa con el usuario para recoger su nombre de usuario y contraseña,
     * y verifica si estas credenciales son válidas.
     */
    public static void run() {
        try {
            printlnGreen("--- VALIDAR ENTRADA DE EMPLEADO ---");
            String nombreUsuario = util.pideTexto("Introduce el nombre de usuario: ");
            String contrasena = util.pideTexto("Introduce la contraseña: ");

            boolean isValid = empleadoService.validarEmpleado(nombreUsuario, contrasena);

            if (isValid) {
                printlnGreen("Empleado validado correctamente.");
            } else {
                printLnRed("Credenciales incorrectos.");
            }
        } catch (Exception e) {
            printLnRed("Error validando el empleado: " + e.getMessage());
        }
    }

}
