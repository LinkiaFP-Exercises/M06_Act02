package orm.view;

import orm.model.EmpleadosDto;
import orm.model.IncidenciasDto;
import orm.service.EmpleadoService;
import orm.service.IncidenciasService;
import orm.utilities.Util;

import java.util.List;

import static orm.utilities.Util.*;


public class GestionEmpleadosIncidencias {

    private static final Util util = new Util();
    private static final EmpleadoService empleadoService = new EmpleadoService();
    private static final IncidenciasService incidenciasService = new IncidenciasService();

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
                    7. Obtener incidencia por ID
                    8. Listar todas las incidencias
                    9. Crear una nueva incidencia
                    10. Incidencias creadas por mí
                    11. Incidencias destinadas a mí
                    0. Salir
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
                    obtenerIncidenciaPorId();
                    break;
                case 8:
                    listarTodasLasIncidencias();
                    break;
                case 9:
                    crearNuevaIncidencia();
                    break;
                case 10:
                    obtenerIncidenciasPorOrigen();
                    break;
                case 11:
                    obtenerIncidenciasPorDestino();
                    break;
                case 0:
                    salir = true;
                    util.close();
                    printlnGreen("Saliendo del programa...");
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


    private static void cambiarContrasenaEmpleado() {
        try {
            printlnGreen("--- CAMBIAR CONTRASEÑA DE EMPLEADO ---");

            EmpleadosDto empleado = buscarEmpleado();

            if (empleado == null) {
                printLnRed("Empleado no encontrado.");
                return;
            }

            String antiguaContrasena = util.pideTexto("Introduce la contraseña antigua: ");
            String nuevaContrasena = util.pideTexto("Introduce la nueva contraseña: ");

            if (!empleado.getContrasena().equals(antiguaContrasena)) {
                printLnRed("La contraseña antigua no coincide.");
                return;
            }

            empleado.setContrasena(nuevaContrasena);
            boolean exito = empleadoService.cambiarContrasenaEmpleadoTrusted(empleado);

            if (exito) {
                printlnGreen("Contraseña cambiada correctamente.");
            } else {
                printLnRed("Error al cambiar la contraseña.");
            }
        } catch (Exception e) {
            printLnRed("Error al cambiar la contraseña: " + e.getMessage());
        }
    }

    private static EmpleadosDto buscarEmpleado() {
        int opcionBusqueda = util.pideEntero("Buscar por (1) ID o (2) Nombre de Usuario: ");
        EmpleadosDto empleado = null;

        if (opcionBusqueda == 1) {
            int idEmpleado = util.pideEntero("Introduce el ID del empleado: ");
            empleado = empleadoService.buscarPorId(idEmpleado);
        } else if (opcionBusqueda == 2) {
            String nombreUsuario = util.pideTexto("Introduce el nombre de usuario del empleado: ");
            empleado = empleadoService.buscarPorUsuario(nombreUsuario);
        } else {
            printLnRed("Opción no válida.");
        }

        if (empleado == null) {
            printLnRed("Empleado no encontrado.");
        }

        return empleado;
    }


    private static void eliminarEmpleado() {
        try {
            printlnGreen("--- ELIMINAR EMPLEADO ---");

            int idEmpleado = util.pideEntero("Introduce el ID del empleado a eliminar: ");
            EmpleadosDto empleado = empleadoService.buscarPorId(idEmpleado);

            if (empleado == null) {
                printLnRed("Empleado no encontrado.");
                return;
            }

            empleadoService.eliminarEmpleado(idEmpleado);
            printlnGreen("Empleado eliminado correctamente.");

        } catch (Exception e) {
            printLnRed("Error eliminando el empleado: " + e.getMessage());
        }
    }

    private static void listarTodosLosEmpleados() {
        printlnGreen("--- LISTADO DE EMPLEADOS ---");
        List<EmpleadosDto> empleados = empleadoService.obtenerTodosLosEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (EmpleadosDto empleado : empleados) {
                printLnYellow(printEmpleado(empleado));
            }
        }
    }

    private static void obtenerIncidenciaPorId() {
        try {
            printlnGreen("--- OBTENER INCIDENCIA POR ID ---");
            int idIncidencia = util.pideEntero("Introduce el ID de la incidencia: ");
            IncidenciasDto incidencia = incidenciasService.buscarIncidenciaPorId(idIncidencia);

            if (incidencia != null) {
                printLnYellow("Incidencia encontrada: ");
                printIncidencia(incidencia);
            } else {
                printLnRed("Incidencia no encontrada.");
            }
        } catch (Exception e) {
            printLnRed("Error al obtener la incidencia: " + e.getMessage());
        }
    }

    private static void listarTodasLasIncidencias() {
        printlnGreen("--- LISTADO DE INCIDENCIAS ---");
        List<IncidenciasDto> incidencias = incidenciasService.obtenerTodasLasIncidencias();
        if (incidencias.isEmpty()) {
            printLnRed("No hay incidencias registradas.");
        } else {
            for (IncidenciasDto incidencia : incidencias) {
                printIncidencia(incidencia);
            }
        }
    }

    private static void crearNuevaIncidencia() {
        try {
            printlnGreen("--- CREAR NUEVA INCIDENCIA ---");
            String nombreUsuarioOrigen = util.pideTexto("Introduce el nombre de usuario del empleado origen: ");
            EmpleadosDto empleadoOrigen = empleadoService.buscarPorUsuario(nombreUsuarioOrigen);
            if (empleadoOrigen == null) {
                printLnRed("Empleado de origen no encontrado.");
                return;
            }

            String nombreUsuarioDestino = util.pideTexto("Introduce el nombre de usuario del empleado destino: ");
            EmpleadosDto empleadoDestino = empleadoService.buscarPorUsuario(nombreUsuarioDestino);
            if (empleadoDestino == null) {
                printLnRed("Empleado de destino no encontrado.");
                return;
            }

            String detalle = util.pideTexto("Introduce el detalle de la incidencia: ");
            String tipo = util.pideTexto("Introduce el tipo de la incidencia (N/U): ");

            IncidenciasDto nuevaIncidencia = new IncidenciasDto();
            nuevaIncidencia.setEmpleadosByIdEmpleadoOrigen(empleadoOrigen);
            nuevaIncidencia.setEmpleadosByIdEmpleadoDestino(empleadoDestino);
            nuevaIncidencia.setDetalle(detalle);
            nuevaIncidencia.setTipo(tipo);
            nuevaIncidencia.setFechaHora(new java.sql.Timestamp(new java.util.Date().getTime()));

            incidenciasService.crearIncidencia(nuevaIncidencia);
            printlnGreen("Incidencia creada correctamente.");
        } catch (Exception e) {
            printLnRed("Error al crear la incidencia: " + e.getMessage());
        }
    }

    private static void obtenerIncidenciasPorOrigen() {
        try {
            printlnGreen("--- OBTENER INCIDENCIAS POR ORIGEN ---");
            int idEmpleadoOrigen = util.pideEntero("Introduce el ID del empleado origen: ");

            EmpleadosDto empleadoOrigen = empleadoService.buscarPorId(idEmpleadoOrigen);
            if (empleadoOrigen == null) {
                printLnRed("Empleado de origen no encontrado.");
                return;
            }

            List<IncidenciasDto> incidenciasPorOrigen = incidenciasService.obtenerIncidenciasPorOrigen(idEmpleadoOrigen);
            if (incidenciasPorOrigen.isEmpty()) {
                printLnRed("No hay incidencias registradas para el empleado de origen con ID: " + idEmpleadoOrigen);
            } else {
                printLnYellow("Incidencias registradas por el empleado de origen con ID: " + idEmpleadoOrigen);
                for (IncidenciasDto incidencia : incidenciasPorOrigen) {
                    printIncidencia(incidencia);
                }
            }
        } catch (Exception e) {
            printLnRed("Error al obtener las incidencias por origen: " + e.getMessage());
        }
    }

    private static void obtenerIncidenciasPorDestino() {
        try {
            printlnGreen("--- OBTENER INCIDENCIAS POR DESTINO ---");
            int idEmpleadoDestino = util.pideEntero("Introduce el ID del empleado destino: ");

            EmpleadosDto empleadoDestino = empleadoService.buscarPorId(idEmpleadoDestino);
            if (empleadoDestino == null) {
                printLnRed("Empleado destino no encontrado.");
                return;
            }

            List<IncidenciasDto> incidenciasPorDestino = incidenciasService.obtenerIncidenciasPorDestino(idEmpleadoDestino);
            if (incidenciasPorDestino.isEmpty()) {
                printLnRed("No hay incidencias registradas para el empleado destino con ID: " + idEmpleadoDestino);
            } else {
                printLnYellow("Incidencias dirigidas al empleado con ID: " + idEmpleadoDestino);
                for (IncidenciasDto incidencia : incidenciasPorDestino) {
                    printIncidencia(incidencia);
                }
            }
        } catch (Exception e) {
            printLnRed("Error al obtener las incidencias por destino: " + e.getMessage());
        }
    }

}
