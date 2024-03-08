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

public class IncidenciasDao {

    private static final Logger log = Logger.getLogger(IncidenciasDao.class.getName());

    public IncidenciasDto obtenerIncidenciaPorId(int idIncidencia) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(IncidenciasDto.class, idIncidencia);
        }
        catch (Exception e) { log.severe(e.getMessage()); return null; }
    }

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

    public void insertarIncidencia(IncidenciasDto incidencia) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(incidencia);
            transaction.commit();
        }
        catch (Exception e) { log.severe(e.getMessage()); }
    }

    public List<IncidenciasDto> encontrarIncidenciasPorOrigen(int idEmpleadoOrigen) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<IncidenciasDto> cq = cb.createQuery(IncidenciasDto.class);
            Root<IncidenciasDto> incidencias = cq.from(IncidenciasDto.class);
            cq.select(incidencias).where(cb.equal(incidencias.get("empleadosByIdEmpleadoOrigen").get("idEmpleado"), idEmpleadoOrigen));
            return session.createQuery(cq).getResultList();
        }
    }

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
