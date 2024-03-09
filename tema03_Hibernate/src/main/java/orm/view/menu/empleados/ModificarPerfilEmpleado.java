package orm.view.menu.empleados;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;
import orm.utilities.Util;

import static orm.utilities.Util.printLnRed;
import static orm.utilities.Util.printlnGreen;
import static orm.view.menu.empleados.CambiarContrasenaEmpleado.buscarEmpleado;

public class ModificarPerfilEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();

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
