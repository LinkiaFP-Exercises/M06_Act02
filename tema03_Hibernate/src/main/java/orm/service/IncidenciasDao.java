package orm.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.config.HibernateUtil;
import orm.model.IncidenciasDto;

import java.util.List;
import java.util.logging.Logger;

/**
 * DAO para la entidad {@link IncidenciasDto}.
 * Ofrece operaciones de base de datos para la entidad Incidencia, permitiendo buscar, insertar y listar incidencias,
 * así como encontrar incidencias por origen o destino. Utiliza Hibernate para la comunicación con la base de datos.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see IncidenciasDto
 * @see HibernateUtil
 */
public class IncidenciasDao {

    private static final Logger log = Logger.getLogger(IncidenciasDao.class.getName());

    /**
     * Obtiene una incidencia por su ID.
     *
     * @param idIncidencia El ID de la incidencia a buscar.
     * @return La {@link IncidenciasDto} encontrada o null si no existe.
     */
    public IncidenciasDto obtenerIncidenciaPorId(int idIncidencia) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(IncidenciasDto.class, idIncidencia);
        }
        catch (Exception e) { log.severe(e.getMessage()); return null; }
    }

    /**
     * Lista todas las incidencias registradas en la base de datos.
     *
     * @return Una lista de {@link IncidenciasDto} con todas las incidencias.
     */
    public List<IncidenciasDto> listarTodasLasIncidencias() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<IncidenciasDto> cq = cb.createQuery(IncidenciasDto.class);
            Root<IncidenciasDto> rootEntry = cq.from(IncidenciasDto.class);
            CriteriaQuery<IncidenciasDto> all = cq.select(rootEntry);

            return session.createQuery(all).getResultList();
        } catch (Exception e) {
            log.severe(e.getMessage());
            return null;
        }
    }

    /**
     * Inserta una nueva incidencia en la base de datos.
     *
     * @param incidencia La {@link IncidenciasDto} a insertar.
     */
    public void insertarIncidencia(IncidenciasDto incidencia) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(incidencia);
            transaction.commit();
        }
        catch (Exception e) { log.severe(e.getMessage()); }
    }

    /**
     * Encuentra incidencias originadas por un empleado originario.
     *
     * @param idEmpleadoOrigen El ID del empleado origen de las incidencias.
     * @return Una lista de {@link IncidenciasDto} originadas por el empleado.
     */
    public List<IncidenciasDto> encontrarIncidenciasPorOrigen(int idEmpleadoOrigen) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<IncidenciasDto> cq = cb.createQuery(IncidenciasDto.class);
            Root<IncidenciasDto> incidencias = cq.from(IncidenciasDto.class);
            cq.select(incidencias).where(cb.equal(incidencias.get("empleadosByIdEmpleadoOrigen").get("idEmpleado"), idEmpleadoOrigen));
            return session.createQuery(cq).getResultList();
        }
    }

    /**
     * Encuentra incidencias destinadas a un empleado destinatario.
     *
     * @param idEmpleadoDestino El ID del empleado destino de las incidencias.
     * @return Una lista de {@link IncidenciasDto} destinadas al empleado.
     */
    public List<IncidenciasDto> encontrarIncidenciasPorDestino(int idEmpleadoDestino) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<IncidenciasDto> cq = cb.createQuery(IncidenciasDto.class);
            Root<IncidenciasDto> incidencias = cq.from(IncidenciasDto.class);
            cq.select(incidencias).where(cb.equal(incidencias.get("empleadosByIdEmpleadoDestino").get("idEmpleado"), idEmpleadoDestino));
            return session.createQuery(cq).getResultList();
        }
    }

}
