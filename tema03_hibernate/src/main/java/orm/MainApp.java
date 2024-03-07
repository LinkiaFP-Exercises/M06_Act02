package orm;

import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.config.HibernateUtil;
import orm.model.EmpleadosDto;

public class MainApp {
    public static void main(String[] args) {
        EmpleadosDto empleado = new EmpleadosDto();
        empleado.setNombreUsuario("testUser");
        empleado.setContrasena("testPass");
        empleado.setNombreCompleto("Test User");
        empleado.setTelefonoContacto("123456789");

        // Guardar el empleado
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(empleado);
            transaction.commit();
            System.out.println("Empleado guardado con éxito");

            // Recuperar y mostrar el empleado guardado
            EmpleadosDto empleadoRecuperado = session.get(EmpleadosDto.class, empleado.getIdEmpleado());
            if (empleadoRecuperado != null) {
                System.out.println("Empleado Recuperado: " + empleadoRecuperado);
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
