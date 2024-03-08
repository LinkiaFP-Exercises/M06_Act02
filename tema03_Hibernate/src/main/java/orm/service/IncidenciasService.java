package orm.service;

import orm.model.IncidenciasDto;

import java.util.List;

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
