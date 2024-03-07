package view;

import service.EmpleadoService;

import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EmpleadoService dao = new EmpleadoService();

    public static  void start() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\nGestión de Empleados:");
            System.out.println("1 - Insertar");
            System.out.println("2 - Modificar");
            System.out.println("3 - Cambiar la contraseña");
            System.out.println("4 - Eliminar");
            System.out.println("5 - Listar");
            System.out.println("0 - Salir");

            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    insertarEmpleado();
                    break;
                case 2:
                    modificarEmpleado();
                    break;
                case 3:
                    cambiarContrasena();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    listarEmpleado();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    private static void listarEmpleado() {
        dao.obtenerTodos().forEach(System.out::println);
    }

    private static void insertarEmpleado() {
        // Aquí iría la lógica para insertar un empleado.
        // Puedes pedir al usuario que introduzca los datos necesarios y luego llamar al método correspondiente.
        System.out.println("Insertar un nuevo empleado:");
        // Ejemplo: solicitar datos del empleado y llamar al método de inserción.
    }

    private static void modificarEmpleado() {
        // Lógica similar para modificar un empleado existente.
        System.out.println("Modificar un empleado existente:");
    }

    private static void cambiarContrasena() {
        // Lógica para cambiar la contraseña de un empleado.
        System.out.println("Cambiar la contraseña de un empleado existente:");
    }

    private static void eliminarEmpleado() {
        // Lógica para eliminar un empleado.
        System.out.println("Eliminar un empleado:");
    }
}
