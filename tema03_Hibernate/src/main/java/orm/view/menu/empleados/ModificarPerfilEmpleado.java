package orm.view.menu.empleados;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;
import static orm.view.menu.empleados.CambiarContrasenaEmpleado.buscarEmpleado;

/**
 * Maneja la funcionalidad para modificar la información del perfil de un empleado existente.
 * Permite al usuario actualizar el nombre de usuario, nombre completo y teléfono de contacto del empleado.
 * Antes de realizar cualquier modificación, se busca al empleado para asegurar que existe en el sistema.
 * <p>
 * Si se proporcionan nuevos valores para los campos, estos se actualizarán; de lo contrario, se mantendrán los actuales.
 * Es una herramienta crucial para mantener actualizada la información del personal en la base de datos.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadosDto
 * @see EmpleadoService
 * @see Util
 */
public class ModificarPerfilEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();

    /**
     * Ejecuta el proceso de actualización del perfil del empleado. Solicita al usuario la nueva información
     * y, si es válida, actualiza el registro del empleado correspondiente.
     */
    public static void run() {
        try {
            printlnGreen("--- MODIFICAR PERFIL DE EMPLEADO ---");

            EmpleadosDto empleado = buscarEmpleado();

            if (empleado == null) {
                printLnRed("Empleado no encontrado.");
                return;
            }

            String nuevoNombreUsuario = util.pideTexto("Introduce el nuevo nombre de usuario (actual: " + empleado.getNombreUsuario() + "): ");
            String nuevoNombreCompleto = util.pideTexto("Introduce el nuevo nombre completo (actual: " + empleado.getNombreCompleto() + "): ");
            String nuevoTelefonoContacto = util.pideTexto("Introduce el nuevo teléfono de contacto (actual: " + empleado.getTelefonoContacto() + "): ");

            empleado.setNombreUsuario(nuevoNombreUsuario.isEmpty() ? empleado.getNombreUsuario() : nuevoNombreUsuario);
            empleado.setNombreCompleto(nuevoNombreCompleto.isEmpty() ? empleado.getNombreCompleto() : nuevoNombreCompleto);
            empleado.setTelefonoContacto(nuevoTelefonoContacto.isEmpty() ? empleado.getTelefonoContacto() : nuevoTelefonoContacto);

            boolean exito = empleadoService.modificarPerfilEmpleado(empleado);

            if (exito) {
                printlnGreen("Perfil del empleado actualizado correctamente.");
            } else {
                printLnRed("Error actualizando el perfil del empleado.");
            }
        } catch (Exception e) {
            printLnRed("Error modificando el perfil del empleado: " + e.getMessage());
        }
    }

}
