package orm;

import orm.config.HibernateUtil;
import orm.testing.TestEmpleadoService;
import orm.testing.TestIncidenciasService;
import orm.view.GestionGeneral;

/**
 * Clase principal que sirve como punto de entrada de la aplicación.
 * Contiene el método main que inicia la ejecución del programa y coordina
 * el flujo de la aplicación.
 * <p>
 * La clase MainApp muestra el menú principal y gestiona las interacciones
 * del usuario, dirigiendo el flujo de la aplicación según las opciones
 * seleccionadas por el usuario.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see GestionGeneral
 * @see HibernateUtil
 * @see TestEmpleadoService
 * @see TestIncidenciasService
 */
public class MainApp {
    public static void main(String[] args) {
        HibernateUtil.executeInitSqlScript();
        TestEmpleadoService.doAll();
        TestIncidenciasService.doAll();
        GestionGeneral.start();
    }

}
