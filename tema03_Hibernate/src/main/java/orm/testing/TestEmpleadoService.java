package orm.testing;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;

import java.util.List;
import java.util.logging.Logger;

public class TestEmpleadoService {

    private static final Logger log = Logger.getLogger(TestEmpleadoService.class.getName());
    private static final EmpleadoService empleadoService = new EmpleadoService();
    private static int usuario_id = -1;
    private static final String USUARIO_TEST = "usuarioTest";
    private static final String CONTRASENA_TEST = "contrasenaTest";
    public static final String NOMBRE_COMPLETO = "Nombre Completo";
    public static final String TELEFONO = "123456789";
    private static EmpleadosDto empleadoMock;

    public static void doAll() {

        testListAll();
        testCreate();
        testReadByUser();
        testReadById();
        testValidarUserPass();
        testUpdate();
        testUpdatePassById();
        testUpdatePassByUser();
        testDelete();
        testListAll();

    }

    private static void testListAll() {
        try {
            printColored("LISTANDO TODOS EMPLEADOS: ", ANSI_YELLOW);
            List<EmpleadosDto> empleados = empleadoService.obtenerTodosLosEmpleados();
            if (empleados != null && empleados.size() == 5)
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }


    private static void testCreate() {
        try {
            empleadoMock = new EmpleadosDto(USUARIO_TEST, CONTRASENA_TEST, NOMBRE_COMPLETO, TELEFONO);
            printColored("INSERTANDO EMPLEADO: ", ANSI_YELLOW);
            empleadoService.insertarEmpleado(empleadoMock);
            printSuccess();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testReadByUser() {
        try {
            printColored("BUSCANDO EMPLEADO POR NOMBRE DE USUARIO: ", ANSI_YELLOW);
            EmpleadosDto empleado = empleadoService.buscarPorUsuario(USUARIO_TEST);
            if (empleado != null) {
                usuario_id = empleado.getIdEmpleado();
                printSuccess();
            }
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testReadById() {
        try {
            printColored("BUSCANDO EMPLEADO POR ID: ", ANSI_YELLOW);
            EmpleadosDto empleado = empleadoService.buscarPorId(usuario_id);
            if (empleado != null ) {
                empleadoMock.setIdEmpleado(empleado.getIdEmpleado());
                if (empleadoMock.equals(empleado))
                    printSuccess();
                else
                    printFail();
            } else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testValidarUserPass() {
        try {
            printColored("VALIDANDO EMPLEADO POR USUARIO Y CONTRASEÑA: ", ANSI_YELLOW);
            if (empleadoService.validarEmpleado(USUARIO_TEST, CONTRASENA_TEST))
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testUpdate() {
        try {
            printColored("ACTUALIZAR EMPLEADO: ", ANSI_YELLOW);
            empleadoMock.setNombreUsuario("X");
            empleadoMock.setContrasena("X");
            empleadoMock.setNombreCompleto("X");
            empleadoMock.setTelefonoContacto("X");
            boolean updated = empleadoService.modificarPerfilEmpleado(empleadoMock);
            EmpleadosDto empleado = empleadoService.buscarPorId(usuario_id);
            empleadoMock.setContrasena(CONTRASENA_TEST);
            if (updated && empleadoMock.equals(empleado))
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testUpdatePassById() {
        try {
            printColored("ACTUALIZAR CONTRASEÑA EMPLEADO POR ID: ", ANSI_YELLOW);
            empleadoService.cambiarContrasenaEmpleado(usuario_id, CONTRASENA_TEST, "X");
            EmpleadosDto empleado = empleadoService.buscarPorId(usuario_id);
            empleadoMock.setContrasena("X");
            if (empleadoMock.equals(empleado))
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testUpdatePassByUser() {
        try {
            printColored("ACTUALIZAR CONTRASEÑA EMPLEADO POR USER: ", ANSI_YELLOW);
            empleadoService.cambiarContrasenaEmpleado("X", "X", CONTRASENA_TEST);
            EmpleadosDto empleado = empleadoService.buscarPorUsuario("X");
            empleadoMock.setContrasena(CONTRASENA_TEST);
            if (empleadoMock.equals(empleado))
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testDelete() {
        try {
            printColored("BORRANDO EMPLEADO: ", ANSI_YELLOW);
            empleadoService.eliminarEmpleado(usuario_id);
            EmpleadosDto empleado = empleadoService.buscarPorId(usuario_id);
            if (empleado == null)
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private static void printColored(String message, String colorCode) {
        System.out.print(colorCode + message + ANSI_RESET);
    }
    
    private static void printFail() {
        printColored("FAILURE!!!\n", ANSI_RED);
    }
    private static void printSuccess() {
        printColored("SUCCESS!!!\n", ANSI_GREEN);
    }
    
    

}
