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
    @JoinColumn(name = "id_empleado_origen", referencedColumnName = "id_empleado")
    private EmpleadosDto empleadosByIdEmpleadoOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado_destino", referencedColumnName = "id_empleado")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncidenciasDto that = (IncidenciasDto) o;

        if (getIdIncidencia() != that.getIdIncidencia()) return false;
        if (!getFechaHora().equals(that.getFechaHora())) return false;
        if (!getEmpleadosByIdEmpleadoOrigen().equals(that.getEmpleadosByIdEmpleadoOrigen())) return false;
        if (!getEmpleadosByIdEmpleadoDestino().equals(that.getEmpleadosByIdEmpleadoDestino())) return false;
        if (!getDetalle().equals(that.getDetalle())) return false;
        return getTipo().equals(that.getTipo());
    }

    @Override
    public int hashCode() {
        int result = getIdIncidencia();
        result = 31 * result + getFechaHora().hashCode();
        result = 31 * result + getEmpleadosByIdEmpleadoOrigen().hashCode();
        result = 31 * result + getEmpleadosByIdEmpleadoDestino().hashCode();
        result = 31 * result + getDetalle().hashCode();
        result = 31 * result + getTipo().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IncidenciasDto{" +
                "idIncidencia=" + idIncidencia +
                ", fechaHora=" + fechaHora +
                ", empleadosByIdEmpleadoOrigen=" + empleadosByIdEmpleadoOrigen +
                ", empleadosByIdEmpleadoDestino=" + empleadosByIdEmpleadoDestino +
                ", detalle='" + detalle + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
