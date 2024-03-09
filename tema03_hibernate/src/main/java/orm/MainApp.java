package orm;

import orm.config.HibernateUtil;
import orm.testing.TestEmpleadoService;
import orm.testing.TestIncidenciasService;
import orm.view.GestionEmpleadosIncidencias;

public class MainApp {
    public static void main(String[] args) {
        HibernateUtil.executeInitSqlScript();
        TestEmpleadoService.doAll();
        TestIncidenciasService.doAll();
        GestionEmpleadosIncidencias.start();
    }

}
