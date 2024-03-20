package orm.service;

import orm.model.IncidenciasDto;

import java.util.List;

/**
 * Servicio que ofrece operaciones para gestionar incidencias.
 * Facilita la creación, búsqueda y obtención de incidencias por diferentes criterios,
 * como por ID, origen y destino. Utiliza {@link IncidenciasDao} para la comunicación
 * con la base de datos y manejar la persistencia de las incidencias.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see IncidenciasDto
 * @see IncidenciasDao
 */
public class IncidenciasService {

    private final IncidenciasDao incidenciasDao = new IncidenciasDao();

    public IncidenciasDto buscarIncidenciaPorId(int idIncidencia) {
        return incidenciasDao.obtenerIncidenciaPorId(idIncidencia);
    }

    public List<IncidenciasDto> obtenerTodasLasIncidencias() {
        return incidenciasDao.listarTodasLasIncidencias();
    }

    public void crearIncidencia(IncidenciasDto incidencia) {
        incidenciasDao.insertarIncidencia(incidencia);
    }

    public List<IncidenciasDto> obtenerIncidenciasPorOrigen(int idEmpleadoOrigen) {
        return incidenciasDao.encontrarIncidenciasPorOrigen(idEmpleadoOrigen);
    }

    public List<IncidenciasDto> obtenerIncidenciasPorDestino(int idEmpleadoDestino) {
        return incidenciasDao.encontrarIncidenciasPorDestino(idEmpleadoDestino);
    }
}
