package orm.view;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;
import orm.utilities.Util;

import java.util.List;


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
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (!salir);
    }

    private static void insertarEmpleado() {
        // Implementación para insertar un empleado
    }

    private static void validarEmpleado() {
        // Implementación para validar la entrada de un empleado
    }

    private static void modificarPerfilEmpleado() {
        // Implementación para modificar el perfil de un empleado
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
                System.out.println(empleado);
            }
        }
    }
}
