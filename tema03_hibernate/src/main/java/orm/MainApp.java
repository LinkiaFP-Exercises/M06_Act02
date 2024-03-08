package orm;

import orm.config.HibernateUtil;
import orm.testing.TestEmpleadoService;
import orm.view.GestionEmpleados;

public class MainApp {
    public static void main(String[] args) {
        HibernateUtil.executeInitSqlScript();
        TestEmpleadoService.doAll();
        GestionEmpleados.start();
    }

}
