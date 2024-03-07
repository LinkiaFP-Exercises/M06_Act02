package orm.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "incidencias", schema = "m06Act02Tema03")
public class IncidenciasDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_incidencia", nullable = false)
    private int idIncidencia;
    @Basic
    @Column(name = "fecha_hora", nullable = false)
    private Timestamp fechaHora;
    @Basic
    @Column(name = "id_empleado_origen", nullable = true)
    private Integer idEmpleadoOrigen;
    @Basic
    @Column(name = "id_empleado_destino", nullable = true)
    private Integer idEmpleadoDestino;
    @Basic
    @Column(name = "detalle", nullable = false, length = 255)
    private String detalle;
    @Basic
    @Column(name = "tipo", nullable = false, length = 1)
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_empleado_origen", referencedColumnName = "id_empleado")
    private EmpleadosDto empleadosByIdEmpleadoOrigen;
    @ManyToOne
    @JoinColumn(name = "id_empleado_destino", referencedColumnName = "id_empleado")
    private EmpleadosDto empleadosByIdEmpleadoDestino;

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

    public Integer getIdEmpleadoOrigen() {
        return idEmpleadoOrigen;
    }

    public void setIdEmpleadoOrigen(Integer idEmpleadoOrigen) {
        this.idEmpleadoOrigen = idEmpleadoOrigen;
    }

    public Integer getIdEmpleadoDestino() {
        return idEmpleadoDestino;
    }

    public void setIdEmpleadoDestino(Integer idEmpleadoDestino) {
        this.idEmpleadoDestino = idEmpleadoDestino;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncidenciasDto that = (IncidenciasDto) o;

        if (getIdIncidencia() != that.getIdIncidencia()) return false;
        if (!getFechaHora().equals(that.getFechaHora())) return false;
        if (!getIdEmpleadoOrigen().equals(that.getIdEmpleadoOrigen())) return false;
        if (!getIdEmpleadoDestino().equals(that.getIdEmpleadoDestino())) return false;
        if (!getDetalle().equals(that.getDetalle())) return false;
        return getTipo().equals(that.getTipo());
    }

    @Override
    public int hashCode() {
        int result = getIdIncidencia();
        result = 31 * result + getFechaHora().hashCode();
        result = 31 * result + getIdEmpleadoOrigen().hashCode();
        result = 31 * result + getIdEmpleadoDestino().hashCode();
        result = 31 * result + getDetalle().hashCode();
        result = 31 * result + getTipo().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IncidenciasDto{" +
                "idIncidencia=" + idIncidencia +
                ", fechaHora=" + fechaHora +
                ", idEmpleadoOrigen=" + idEmpleadoOrigen +
                ", idEmpleadoDestino=" + idEmpleadoDestino +
                ", detalle='" + detalle + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

}
