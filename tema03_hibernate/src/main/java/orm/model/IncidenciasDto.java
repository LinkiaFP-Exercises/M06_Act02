package orm.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Table(name = "incidencias", schema = "m06Act02Tema03")
public class IncidenciasDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia", nullable = false)
    private int idIncidencia;

    @Column(name = "fecha_hora", nullable = false)
    private Timestamp fechaHora;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado_origen", referencedColumnName = "id_empleado", insertable = false, updatable = false)
    private EmpleadosDto empleadosByIdEmpleadoOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado_destino", referencedColumnName = "id_empleado", insertable = false, updatable = false)
    private EmpleadosDto empleadosByIdEmpleadoDestino;

    @Column(name = "detalle", nullable = false)
    private String detalle;

    @Column(name = "tipo", nullable = false, length = 1)
    private String tipo;


    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EmpleadosDto getEmpleadosByIdEmpleadoOrigen() {
        return empleadosByIdEmpleadoOrigen;
    }

    public void setEmpleadosByIdEmpleadoOrigen(EmpleadosDto empleadosByIdEmpleadoOrigen) {
        this.empleadosByIdEmpleadoOrigen = empleadosByIdEmpleadoOrigen;
    }

    public EmpleadosDto getEmpleadosByIdEmpleadoDestino() {
        return empleadosByIdEmpleadoDestino;
    }

    public void setEmpleadosByIdEmpleadoDestino(EmpleadosDto empleadosByIdEmpleadoDestino) {
        this.empleadosByIdEmpleadoDestino = empleadosByIdEmpleadoDestino;
    }

    public Integer getIdEmpleadoOrigen() {
        return empleadosByIdEmpleadoOrigen != null ? empleadosByIdEmpleadoOrigen.getIdEmpleado() : null;
    }

    public Integer getIdEmpleadoDestino() {
        return empleadosByIdEmpleadoDestino != null ? empleadosByIdEmpleadoDestino.getIdEmpleado() : null;
    }

}
