package orm.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import orm.config.HibernateUtil;
import orm.model.EmpleadosDto;

import java.util.List;
import java.util.logging.Logger;

public class EmpleadoDao {

    private static final Logger log = Logger.getLogger(EmpleadoDao.class.getName());

    public EmpleadosDto buscarEmpleadoPorId(int idEmpleado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(EmpleadosDto.class, idEmpleado);
        }
        catch (Exception e) { log.severe(e.getMessage()); }
        return null;
    }

    public EmpleadosDto buscarEmpleadoPorNombreUsuario(String nombreUsuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.byNaturalId(EmpleadosDto.class)
                    .using("nombreUsuario", nombreUsuario)
                    .load();
        }
        catch (Exception e) { log.severe(e.getMessage()); return null; }
    }

    public void guardarEmpleado(EmpleadosDto empleado) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(empleado);
            transaction.commit();
        }
        catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    public EmpleadosDto buscarEmpleadoPorCredenciales(String usuario, String contrasena) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EmpleadosDto> cq = cb.createQuery(EmpleadosDto.class);
            Root<EmpleadosDto> root = cq.from(EmpleadosDto.class);

            Predicate condicionUsuario = cb.equal(root.get("nombreUsuario"), usuario);
            Predicate condicionContrasena = cb.equal(root.get("contrasena"), contrasena);
            cq.where(cb.and(condicionUsuario, condicionContrasena));

            return session.createQuery(cq).uniqueResult();
        }
        catch (Exception e) { log.severe(e.getMessage()); return null; }
    }

    public boolean actualizarEmpleado(EmpleadosDto empleado) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            EmpleadosDto empleadoActual = session.get(EmpleadosDto.class, empleado.getIdEmpleado());
            if (notNullOrBlank(empleado.getNombreUsuario()))
                empleadoActual.setNombreUsuario(empleado.getNombreUsuario());
            if (notNullOrBlank(empleado.getNombreCompleto()))
                empleadoActual.setNombreCompleto(empleado.getNombreCompleto());
            if (notNullOrBlank(empleado.getTelefonoContacto()))
                empleadoActual.setTelefonoContacto(empleado.getTelefonoContacto());
            EmpleadosDto updated = session.merge(empleadoActual);
            transaction.commit();
            return updated != null;
        }
        catch (Exception e) { log.severe(e.getMessage()); return false;}
    }

    public void actualizarContrasena(EmpleadosDto empleado) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            EmpleadosDto empleadoActual = session.get(EmpleadosDto.class, empleado.getIdEmpleado());
            if (notNullOrBlank(empleado.getContrasena()))
                empleadoActual.setContrasena(empleado.getContrasena());
            session.merge(empleadoActual);
            transaction.commit();
        }
        catch (Exception e) { log.severe(e.getMessage()); }
    }
    public boolean actualizarContrasenaTrusted(EmpleadosDto empleado) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            EmpleadosDto updated = session.merge(empleado);
            transaction.commit();
            return updated != null;
        }
        catch (Exception e) { log.severe(e.getMessage()); return false;}
    }

    public void eliminarEmpleado(EmpleadosDto empleado) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(empleado);
            transaction.commit();
        }
        catch (Exception e) { log.severe(e.getMessage()); }
    }

    public List<EmpleadosDto> listarEmpleados() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EmpleadosDto> cq = cb.createQuery(EmpleadosDto.class);
            Root<EmpleadosDto> root = cq.from(EmpleadosDto.class);
            cq.select(root);
            Query<EmpleadosDto> query = session.createQuery(cq);
            return query.getResultList();
        }
        catch (Exception e) { log.severe(e.getMessage()); return null; }
    }

    private boolean notNullOrBlank(String value) {
        return value != null && !value.isBlank() && !value.isEmpty();
    }

}
