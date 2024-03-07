package orm.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "empleados", schema = "m06Act02Tema03")
public class EmpleadosDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_empleado", nullable = false)
    private int idEmpleado;
    @Basic
    @Column(name = "nombre_usuario", nullable = false, length = 255)
    private String nombreUsuario;
    @Basic
    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;
    @Basic
    @Column(name = "nombre_completo", nullable = false, length = 255)
    private String nombreCompleto;
    @Basic
    @Column(name = "telefono_contacto", nullable = true, length = 20)
    private String telefonoContacto;
    @OneToMany(mappedBy = "empleadosByIdEmpleadoOrigen")
    private Collection<IncidenciasDto> incidenciasByIdEmpleado;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public Collection<IncidenciasDto> getIncidenciasByIdEmpleado() {
        return incidenciasByIdEmpleado;
    }

    public void setIncidenciasByIdEmpleado(Collection<IncidenciasDto> incidenciasByIdEmpleado) {
        this.incidenciasByIdEmpleado = incidenciasByIdEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadosDto that = (EmpleadosDto) o;

        if (getIdEmpleado() != that.getIdEmpleado()) return false;
        if (!getNombreUsuario().equals(that.getNombreUsuario())) return false;
        if (!getContrasena().equals(that.getContrasena())) return false;
        return getNombreCompleto().equals(that.getNombreCompleto());
    }

    @Override
    public int hashCode() {
        int result = getIdEmpleado();
        result = 31 * result + getNombreUsuario().hashCode();
        result = 31 * result + getContrasena().hashCode();
        result = 31 * result + getNombreCompleto().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EmpleadosDto{" +
                "idEmpleado=" + idEmpleado +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                '}';
    }

}
