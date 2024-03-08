package orm.testing;

import orm.model.EmpleadosDto;
import orm.service.EmpleadoService;

import java.util.List;
import java.util.logging.Logger;

import static orm.utilities.Util.*;

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
            printYellow("LISTANDO TODOS EMPLEADOS: ");
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
            printYellow("INSERTANDO EMPLEADO: ");
            empleadoService.insertarEmpleado(empleadoMock);
            printSuccess();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testReadByUser() {
        try {
            printYellow("BUSCANDO EMPLEADO POR NOMBRE DE USUARIO: ");
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
            printYellow("BUSCANDO EMPLEADO POR ID: ");
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
            printYellow("VALIDANDO EMPLEADO POR USUARIO Y CONTRASEÑA: ");
            if (empleadoService.validarEmpleado(USUARIO_TEST, CONTRASENA_TEST))
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testUpdate() {
        try {
            printYellow("ACTUALIZAR EMPLEADO: ");
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
            printYellow("ACTUALIZAR CONTRASEÑA EMPLEADO POR ID: ");
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
            printYellow("ACTUALIZAR CONTRASEÑA EMPLEADO POR USER: ");
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
            printYellow("BORRANDO EMPLEADO: ");
            empleadoService.eliminarEmpleado(usuario_id);
            EmpleadosDto empleado = empleadoService.buscarPorId(usuario_id);
            if (empleado == null)
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void printFail() { printLnRed("FAILURE!!!"); }
    private static void printSuccess() { printlnGreen("SUCCESS!!!"); }

}
