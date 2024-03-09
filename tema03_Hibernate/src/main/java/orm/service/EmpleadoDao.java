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

/**
 * DAO para la entidad {@link EmpleadosDto}.
 * Ofrece operaciones de base de datos para la entidad Empleado, como buscar, guardar, actualizar y eliminar empleados.
 * Utiliza Hibernate para interactuar con la base de datos.
 *
 * @author <a href="https://about.me/prof.guazina">"Fauno Guazina</a>
 * @see EmpleadosDto
 * @see HibernateUtil
 */
public class EmpleadoDao {

    private static final Logger log = Logger.getLogger(EmpleadoDao.class.getName());

    /**
     * Busca un empleado por su ID.
     *
     * @param idEmpleado ID del empleado a buscar.
     * @return El {@link EmpleadosDto} encontrado o null si no existe.
     */
    public EmpleadosDto buscarEmpleadoPorId(int idEmpleado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(EmpleadosDto.class, idEmpleado);
        }
        catch (Exception e) { log.severe(e.getMessage()); }
        return null;
    }

    /**
     * Busca un empleado por su nombre de usuario.
     *
     * @param nombreUsuario Nombre de usuario del empleado a buscar.
     * @return El {@link EmpleadosDto} encontrado o null si no existe.
     */
    public EmpleadosDto buscarEmpleadoPorNombreUsuario(String nombreUsuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.byNaturalId(EmpleadosDto.class)
                    .using("nombreUsuario", nombreUsuario)
                    .load();
        }
        catch (Exception e) { log.severe(e.getMessage()); return null; }
    }

    /**
     * Guarda un nuevo empleado en la base de datos.
     *
     * @param empleado El {@link EmpleadosDto} a guardar.
     */
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

    /**
     * Busca un empleado por sus credenciales de acceso.
     *
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña.
     * @return El {@link EmpleadosDto} encontrado o null si las credenciales no son válidas.
     */
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

    /**
     * Actualiza la información de un empleado existente, pero no la contraseña.
     *
     * @param empleado El {@link EmpleadosDto} con la información actualizada.
     * @return true si el empleado fue actualizado correctamente, false en caso contrario.
     */
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

    /**
     * Actualiza la contraseña de un empleado.
     *
     * @param empleado El {@link EmpleadosDto} con la nueva contraseña.
     */
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

    /**
     * Actualiza la contraseña de un empleado sin verificar la antigua.
     * Suponiendo que la verificación fue hecha en el front.
     *
     * @param empleado El {@link EmpleadosDto} con la nueva contraseña.
     */
    public boolean actualizarContrasenaTrusted(EmpleadosDto empleado) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            EmpleadosDto updated = session.merge(empleado);
            transaction.commit();
            return updated != null;
        }
        catch (Exception e) { log.severe(e.getMessage()); return false; }
    }

    /**
     * Elimina un empleado de la base de datos.
     *
     * @param empleado El {@link EmpleadosDto} a eliminar.
     */
    public void eliminarEmpleado(EmpleadosDto empleado) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(empleado);
            transaction.commit();
        }
        catch (Exception e) { log.severe(e.getMessage()); }
    }

    /**
     * Lista todos los empleados en la base de datos.
     *
     * @return Una lista de {@link EmpleadosDto} con todos los empleados.
     */
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
