package orm.view.menu.empleados;

import orm.service.EmpleadoService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;

public class ValidarEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();

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
