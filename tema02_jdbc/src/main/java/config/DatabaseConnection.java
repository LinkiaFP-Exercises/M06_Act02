package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Proporciona una conexión a la base de datos de la empresa. Esta clase utiliza el patrón Singleton
 * para asegurar que solo exista una instancia de conexión a la base de datos durante la ejecución del programa.
 * <p>
 * La conexión se establece con la base de datos MySQL localizada en localhost, utilizando el puerto 3306.
 * Esta específicamente diseñada para acceder a la base de datos denominada "empresa".
 * </p>
 * <p>
 * Es importante asegurar que el servicio de MySQL esté en ejecución y que la base de datos "empresa"
 * esté correctamente configurada antes de utilizar esta clase para obtener conexiones.
 * </p>
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see java.sql.DriverManager#getConnection(String, String, String)
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    /**
     * Obtiene una conexión a la base de datos "empresa". Utiliza las credenciales por defecto
     * para establecer la conexión.
     * <p>
     * En caso de no poder establecer una conexión debido a un error de SQL, este método lanzará
     * una excepción en tiempo de ejecución indicando el error específico que ocurrió durante el intento
     * de conexión.
     * </p>
     *
     * @return Una instancia de {@link Connection} que representa la conexión a la base de datos.
     * @throws RuntimeException si ocurre un error de SQL durante el establecimiento de la conexión.
     * @see DriverManager#getConnection(String, String, String)
     */
    public static Connection get() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error conectando a la base de datos", e);
        }
    }
}
