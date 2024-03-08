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
        testCrearIncidencia();
        testObtenerIncidenciaPorId();
        testListarTodasLasIncidencias();
        testObtenerIncidenciasPorOrigen();
        testObtenerIncidenciasPorDestino();
    }

    private static void testCrearIncidencia() {
        printYellow("TEST: Crear Incidencia");
        // Aquí tu código para crear una nueva incidencia y validar la operación
    }

    private static void testObtenerIncidenciaPorId() {
        printYellow("TEST: Obtener Incidencia Por Id");
        // Aquí tu código para obtener una incidencia por su ID y validar la operación
    }

    private static void testListarTodasLasIncidencias() {
        printYellow("TEST: Listar Todas Las Incidencias");
        // Aquí tu código para listar todas las incidencias y validar la operación
    }

    private static void testObtenerIncidenciasPorOrigen() {
        printYellow("TEST: Obtener Incidencias Por Origen");
        // Aquí tu código para obtener incidencias por el empleado origen y validar la operación
    }

    private static void testObtenerIncidenciasPorDestino() {
        printYellow("TEST: Obtener Incidencias Por Destino");
        // Aquí tu código para obtener incidencias destinadas a un empleado específico y validar la operación
    }

    private static void printSuccess() {
        printlnGreen("SUCCESS!!!");
    }

    // Método para imprimir fallo
    private static void printFail() {
        printLnRed("FAILURE!!!");
    }
}
