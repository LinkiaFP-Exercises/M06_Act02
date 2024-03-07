package config;


import java.sql.*;
import java.util.logging.Logger;

public class DatabaseConnectionTest {
    private static final Logger log = Logger.getLogger(DatabaseConnectionTest.class.getName());

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
