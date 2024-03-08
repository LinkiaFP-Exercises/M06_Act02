package orm.view;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;
import orm.utilities.Util;

import java.util.List;

import static orm.utilities.Util.*;


public class GestionEmpleados {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();

    public static void start() {
        boolean salir = false;
        do {
            System.out.println("""
                    
                    --- Gestión de Empleados ---
                    1. Insertar empleado
                    2. Validar entrada de empleado
                    3. Modificar perfil de empleado
                    4. Cambiar contraseña de empleado
                    5. Eliminar empleado
                    6. Listar todos los empleados
                    7. Salir
                    """);


            switch (util.pideEntero("Seleccione una opción: ")) {
                case 1:
                    insertarEmpleado();
                    break;
                case 2:
                    validarEmpleado();
                    break;
                case 3:
                    modificarPerfilEmpleado();
                    break;
                case 4:
                    cambiarContrasenaEmpleado();
                    break;
                case 5:
                    eliminarEmpleado();
                    break;
                case 6:
                    listarTodosLosEmpleados();
                    break;
                case 7:
                    salir = true;
                    util.close();
                    printYellow("Saliendo del programa...");
                    break;
                default:
                    printLnRed("Opción no válida, por favor intente nuevamente.");
            }
        } while (!salir);
    }

    private static void insertarEmpleado() {
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

    private static void validarEmpleado() {
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

    private static void modificarPerfilEmpleado() {
        try {
            printlnGreen("--- MODIFICAR PERFIL DE EMPLEADO ---");

            int opcionBusqueda = util.pideEntero("Buscar por (1) ID o (2) Nombre de Usuario: ");
            EmpleadosDto empleado;

            if (opcionBusqueda == 1) {
                int idEmpleado = util.pideEntero("Introduce el ID del empleado: ");
                empleado = empleadoService.buscarPorId(idEmpleado);
            } else if (opcionBusqueda == 2) {
                String nombreUsuario = util.pideTexto("Introduce el nombre de usuario del empleado: ");
                empleado = empleadoService.buscarPorUsuario(nombreUsuario);
            } else {
                printLnRed("Opción no válida.");
                return;
            }

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

    private static void cambiarContrasenaEmpleado() {
        // Implementación para cambiar la contraseña de un empleado
    }

    private static void eliminarEmpleado() {
        // Implementación para eliminar un empleado
    }

    private static void listarTodosLosEmpleados() {
        List<EmpleadosDto> empleados = empleadoService.obtenerTodosLosEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (EmpleadosDto empleado : empleados) {
                System.out.println(printEmpleado(empleado));
            }
        }
    }
}
