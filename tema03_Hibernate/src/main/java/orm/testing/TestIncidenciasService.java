package orm.testing;

import orm.model.IncidenciasDto;
import orm.model.EmpleadosDto;
import orm.service.IncidenciasService;
import orm.service.EmpleadoService;

import java.util.List;
import java.util.logging.Logger;

import static orm.utilities.Util.*;

public class TestIncidenciasService {

    private static final Logger log = Logger.getLogger(TestIncidenciasService.class.getName());
    private static final IncidenciasService incidenciasService = new IncidenciasService();
    private static final EmpleadoService empleadoService = new EmpleadoService();

    public static void doAll() {
        testListarTodasLasIncidencias();
        testCrearIncidencia();
        testObtenerIncidenciaPorId();
        testObtenerIncidenciasPorOrigen();
        testObtenerIncidenciasPorDestino();
    }

    private static void testListarTodasLasIncidencias() {
        try {
            printYellow("LISTANDO TODAS LAS INCIDENCIAS: ");
            List<IncidenciasDto> incidencias = incidenciasService.obtenerTodasLasIncidencias();
            if (incidencias != null && !incidencias.isEmpty())
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testCrearIncidencia() {
        try {
            // Asumiendo que ya tienes un método para buscar empleados por nombre de usuario
            EmpleadosDto empleadoOrigen = empleadoService.buscarPorUsuario("agonzalez");
            EmpleadosDto empleadoDestino = empleadoService.buscarPorUsuario("jramirez");

            if (empleadoOrigen == null || empleadoDestino == null) {
                printLnRed("Empleado de origen o destino no encontrado.");
                return;
            }

            IncidenciasDto nuevaIncidencia = new IncidenciasDto();
            // Configura la fecha y hora actual para la incidencia
            nuevaIncidencia.setFechaHora(new java.sql.Timestamp(new java.util.Date().getTime()));
            nuevaIncidencia.setEmpleadosByIdEmpleadoOrigen(empleadoOrigen);
            nuevaIncidencia.setEmpleadosByIdEmpleadoDestino(empleadoDestino);
            nuevaIncidencia.setDetalle("Detalle de prueba para la incidencia");
            nuevaIncidencia.setTipo("N"); // N para normal, U para urgente

            printYellow("INSERTANDO INCIDENCIA: ");
            incidenciasService.crearIncidencia(nuevaIncidencia);
            printSuccess();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testObtenerIncidenciaPorId() {
        try {
            printYellow("BUSCANDO INCIDENCIA POR ID: ");
            int idIncidencia = 1;
            IncidenciasDto incidencia = incidenciasService.buscarIncidenciaPorId(idIncidencia);
            if (incidencia != null) {
                printSuccess();
            } else {
                printFail();
            }
        } catch (Exception e) { log.severe(e.getMessage()); }
    }


    private static void testObtenerIncidenciasPorOrigen() {
        try {
            printYellow("BUSCANDO INCIDENCIAS ORIGINADAS POR EMPLEADO POR ID: ");
            int idEmpleadoOrigen = 1;
            List<IncidenciasDto> incidencias = incidenciasService.obtenerIncidenciasPorOrigen(idEmpleadoOrigen);
            if (incidencias != null && !incidencias.isEmpty()) {
                printSuccess();
            } else {
                printFail();
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
            printFail();
        }
    }


    private static void testObtenerIncidenciasPorDestino() {
        try {
            printYellow("BUSCANDO INCIDENCIAS DESTINADAS AL EMPLEADO POR ID: ");
            int idEmpleadoDestino = 1;
            List<IncidenciasDto> incidencias = incidenciasService.obtenerIncidenciasPorOrigen(idEmpleadoDestino);
            if (incidencias != null && !incidencias.isEmpty()) {
                printSuccess();
            } else {
                printFail();
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
            printFail();
        }
    }

    private static void printSuccess() {
        printlnGreen("SUCCESS!!!");
    }

    // Método para imprimir fallo
    private static void printFail() {
        printLnRed("FAILURE!!!");
    }
}
