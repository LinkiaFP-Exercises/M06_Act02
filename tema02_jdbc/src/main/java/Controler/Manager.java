package Controler;

import config.DatabaseConnectionTest;
import view.Menu;

/**
 * Clase principal que inicia la aplicación de gestión de empleados.
 * Establece la conexión con la base de datos mediante {@link DatabaseConnectionTest}
 * y lanza el menú principal de la aplicación a través de {@link Menu}.
 * <p>
 * Esta clase es el punto de entrada del programa, encargándose de iniciar los componentes necesarios
 * para que el usuario interactúe con el sistema de gestión de empleados.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 */
public class Manager {

    public static void main(String[] args) {
        DatabaseConnectionTest.testConnection();
        Menu.start();
    }
}
