package orm;

import orm.config.HibernateUtil;
import orm.testing.TestEmpleadoService;
import orm.testing.TestIncidenciasService;
import orm.view.GestionGeneral;

public class MainApp {
    public static void main(String[] args) {
        HibernateUtil.executeInitSqlScript();
        TestEmpleadoService.doAll();
        TestIncidenciasService.doAll();
        GestionGeneral.start();
    }

}
