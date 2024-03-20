package orm.view.menu.empleados;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;

/**
 * Permite la inserción de nuevos empleados en el sistema.
 * Solicita al usuario los datos necesarios para registrar un nuevo empleado, como nombre de usuario,
 * contraseña, nombre completo y teléfono de contacto, y procede con la inserción a través del {@link EmpleadoService}.
 * <p>
 * Esta clase es un ejemplo de cómo se implementan operaciones de creación de registros dentro de la aplicación,
 * asegurando que la información requerida sea recopilada de manera eficiente y segura antes de realizar la inserción.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadosDto
 * @see EmpleadoService
 * @see Util
 */
public class InsertarEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();

    /**
     * Ejecuta el proceso de recopilación de datos del nuevo empleado y su inserción en la base de datos.
     * Interactúa con el usuario para obtener la información necesaria y utiliza {@link EmpleadoService}
     * para llevar a cabo la operación de inserción.
     */
    public static void run() {
        try {
            printlnGreen("--- INSERTAR NUEVO EMPLEADO ---");
            String nombreUsuario = util.pideTexto("Introduce el nombre de usuario: ");
            String contrasena = util.pideTexto("Introduce la contraseña: ");
            String nombreCompleto = util.pideTexto("Introduce el nombre completo: ");
            String telefonoContacto = util.pideTexto("Introduce el teléfono de contacto: ");

            EmpleadosDto nuevoEmpleado = new EmpleadosDto(nombreUsuario, contrasena, nombreCompleto, telefonoContacto);

            empleadoService.insertarEmpleado(nuevoEmpleado);
            printlnGreen("Empleado insertado correctamente.");
        } catch (Exception e) {
            printLnRed("Error insertando el empleado: " + e.getMessage());
        }
    }
}
