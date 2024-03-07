package view;

import model.Empleado;
import service.EmpleadoService;

import java.util.Scanner;
import java.util.logging.Logger;

public class Menu {
    private static final Logger log = Logger.getLogger(Menu.class.getName());
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
            System.out.println("5 - Mostrar");
            System.out.println("6 - Listar todos");
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
                    mostrarEmpleado();
                    break;
                case 6:
                    listarTodosEmpleados();
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

    private static void insertarEmpleado() {
        System.out.print("Ingrese el nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese la nueva contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Ingrese el nombre completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Ingrese el numero de telefono: ");
        String telefono = scanner.nextLine();

        Empleado empleado = new Empleado(usuario, contrasena, nombreCompleto, telefono);
        int affectedRows = dao.insertar(empleado);

        if (affectedRows > 0) {
            log.info("Empleado insertado con éxito.");
        } else {
            log.warning("No ha sido posible insertar el empleado.");
        }

    }

    private static void modificarEmpleado() {
        Empleado empleado = mostrarEmpleado();

        System.out.println("ENTRE NUEVOS DATOS:");
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Teléfono de contacto: ");
        String telefonoContacto = scanner.nextLine();

        Empleado alterado = new Empleado(empleado.getIdEmpleado(), nombreUsuario, contrasena, nombreCompleto, telefonoContacto);

        int affectedRows = dao.modificar(alterado);
        if (affectedRows > 0) {
            log.info("Empleado con ID: " + empleado.getIdEmpleado() + " modificado con éxito.");
        } else {
            log.warning("No se encontró el empleado con ID: " + empleado.getIdEmpleado() + " para modificar.");
        }
    }

    private static void cambiarContrasena() {

        System.out.print("Ingrese el ID del empleado cuya contraseña desea cambiar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la nueva contraseña: ");
        String nuevaContrasena = scanner.nextLine();

        int affectedRows = dao.cambiarContrasena(id, nuevaContrasena);

        if (affectedRows > 0) {
            log.info("Contraseña actualizada con éxito para el empleado con ID: " + id);
        } else {
            log.warning("No se encontró el empleado con ID: " + id);
        }

        System.out.println("La contraseña ha sido actualizada.");
    }

    private static void eliminarEmpleado() {
        System.out.print("Ingrese el ID del empleado que desea eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        int affectedRows = dao.eliminar(id);
        if (affectedRows > 0) {
            log.info("Empleado eliminado con éxito. ID: " + id);
        } else {
            log.warning("No se encontró el empleado con ID: " + id + " para eliminar.");
        }
    }

    private static Empleado mostrarEmpleado() {
        System.out.print("Introduce el ID del empleado: ");
        int id = Integer.parseInt(scanner.nextLine());
        Empleado empleado = dao.obtenerPorId(id);
        if (empleado != null) {
            log.info("Empleado encontrado: " + empleado);
        } else {
            log.warning("No se encontró ningún empleado con el ID: " + id);
        }
        return empleado;
    }

    private static void listarTodosEmpleados() {
        dao.obtenerTodos().forEach(System.out::println);
    }
}
