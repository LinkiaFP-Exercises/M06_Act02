package orm.service;

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
            return session.createQuery("from IncidenciasDto", IncidenciasDto.class).list();
        }
        catch (Exception e) { log.severe(e.getMessage()); return null; }
    }

    public void insertarIncidencia(IncidenciasDto incidencia) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(incidencia);
            transaction.commit();
        }
        catch (Exception e) { log.severe(e.getMessage()); }
    }
}
