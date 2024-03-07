package config;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnectionTest {
    public static void testConnection() {
        Connection conn = null;
        try {
            // Intenta establecer la conexión con la base de datos
            conn = DatabaseConnection.get();
            System.out.println("Conexión a la base de datos exitosa!");

            // Opcional: Ejecuta una consulta simple para verificar la conexión
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 1");
            if (resultSet.next()) {
                System.out.println("Consulta ejecutada con éxito, conexión verificada.");
            }

        } catch (Exception e) {
            // Captura cualquier excepción y la imprime
            System.out.println("No se pudo conectar a la base de datos.");
            e.printStackTrace();
        } finally {
            // Cierra la conexión si está abierta
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("TestConexión a la base de datos cerrada.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
