package orm.utilities;

import orm.model.EmpleadosDto;
import orm.model.IncidenciasDto;

import java.util.Scanner;

public class Util {
    private final Scanner scanner;

    public Util() {
        scanner = new Scanner(System.in);
    }

    public int pideEntero(String enunciado) {
        while (true) {
            try {
                printYellow(enunciado);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("El valor entrado no es válido!");
            }
        }
    }
    public String pideTexto(String enunciado) {
        while (true) {
            try {
                printYellow(enunciado);
                String texto = scanner.nextLine();
                if (texto == null || texto.isEmpty() || texto.isBlank())
                    throw new Exception("La entrada no puede ser vacia");
                return texto;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void close() {
        scanner.close();
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void printColored(String message, String colorCode) {
        System.out.print(colorCode + message + ANSI_RESET);
    }
    public static void printLnRed(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }
    public static void printlnGreen(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }
    public static void printYellow(String message) {
        System.out.print(ANSI_YELLOW + message + ANSI_RESET);
    }
    public static void printLnYellow(String message) { System.out.println(ANSI_YELLOW + message + ANSI_RESET); }

    public static String printEmpleado(EmpleadosDto subject) {
        return String.format("%-10s | %-20s | %-30s | %-15s",
                subject.getIdEmpleado(), subject.getNombreUsuario(), subject.getNombreCompleto(), subject.getTelefonoContacto());
    }

    public static void printIncidencia(IncidenciasDto subject) {
        String origen = subject.getEmpleadosByIdEmpleadoOrigen() != null
                ? subject.getEmpleadosByIdEmpleadoOrigen().getNombreCompleto() : "Desconocido";
        String destino = subject.getEmpleadosByIdEmpleadoDestino() != null
                ? subject.getEmpleadosByIdEmpleadoDestino().getNombreCompleto() : "Desconocido";
        String fechaHora = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(subject.getFechaHora());

        String header = String.format("%-5s | %-20s | %-20s | %-5s", subject.getIdIncidencia(), fechaHora, origen, destino, subject.getTipo());
        String detalle = "Detalle: " + subject.getDetalle();

        printLnYellow(header);
        printLnRed(detalle);
    }

}
