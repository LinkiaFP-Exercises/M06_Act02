package config;


import java.sql.*;
import java.util.logging.Logger;

/**
 * Clase para probar la conexión con la base de datos de la empresa y realizar una inicialización básica.
 * Contiene métodos para testear la conexión a la base de datos y para inicializar la base de datos con datos de prueba.
 * Utiliza {@link DatabaseConnection} para obtener la conexión a la base de datos.
 * <p>
 * La inicialización de la base de datos incluye la creación de una tabla de empleados y la inserción de algunos registros de prueba.
 * Esta clase es útil para verificar que la conexión a la base de datos funciona como se espera y que la base de datos está correctamente configurada.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 */
public class DatabaseConnectionTest {
    private static final Logger log = Logger.getLogger(DatabaseConnectionTest.class.getName());

    /**
     * Testea la conexión con la base de datos y realiza una serie de operaciones de prueba,
     * incluyendo la inicialización de la base de datos con datos de prueba.
     * <p>
     * Registra información sobre el éxito o fracaso de las operaciones, incluyendo la conexión y consulta a la base de datos,
     * así como la inicialización de la misma con datos de prueba.
     */
    public static void testConnection() {
        Connection conn = null;
        try {
            conn = DatabaseConnection.get();
            System.out.println("Conexión a la base de datos exitosa!");
            System.out.println("INSERTANDO DATOS DE PRUEBA!");
            inicializarBaseDeDatos(conn);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 1");
            if (resultSet.next()) {
                log.info("Consulta ejecutada con éxito, conexión verificada.");
            }

        } catch (Exception e) {
            log.warning("Error al testar la conexión: " + e.getMessage());

        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("TestConexión a la base de datos cerrada.");
                }
            } catch (Exception e) {
                log.warning("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Inicializa la base de datos con una configuración de prueba, incluyendo la creación de una nueva base de datos,
     * una tabla de empleados, y la inserción de registros de prueba en dicha tabla.
     * <p>
     * Este método es llamado durante el proceso de testeo de la conexión para verificar que la base de datos
     * puede ser modificada y consultada correctamente.
     *
     * @param conn La conexión a la base de datos que será utilizada para inicializar la base de datos.
     */
    public static void inicializarBaseDeDatos(Connection conn) {
        String[] sqlStatements = {
                "DROP DATABASE IF EXISTS empresa",
                "CREATE DATABASE empresa",
                "USE empresa",
                "CREATE TABLE empleados (" +
                        "id_empleado INT AUTO_INCREMENT PRIMARY KEY," +
                        "nombre_usuario VARCHAR(255) NOT NULL," +
                        "contrasena VARCHAR(255) NOT NULL," +
                        "nombre_completo VARCHAR(255) NOT NULL," +
                        "telefono_contacto VARCHAR(20)" +
                        ")",
                "INSERT INTO empleados (nombre_usuario, contrasena, nombre_completo, telefono_contacto) VALUES" +
                        "('usuario1', 'contrasena1', 'Juan Pérez', '1234567890')," +
                        "('usuario2', 'contrasena2', 'Ana Gómez', '0987654321')," +
                        "('usuario3', 'contrasena3', 'Luis Martínez', '1122334455')"
        };

        try (Statement stmt = conn.createStatement()) {

            for (String sql : sqlStatements) {
                if (sql.startsWith("USE")) {
                    conn.setCatalog("empresa");
                } else {
                    stmt.execute(sql);
                }
            }
            log.info("Base de datos 'empresa' inicializada con éxito.");
        } catch (SQLException e) {
            log.severe("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}
