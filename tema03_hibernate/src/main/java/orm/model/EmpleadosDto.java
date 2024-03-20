package orm.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.Collection;

/**
 * Clase que representa la entidad de empleados en la base de datos.
 * Contiene información sobre los empleados, como su ID, nombre de usuario, contraseña,
 * nombre completo y teléfono de contacto. Esta clase se utiliza para mapear los datos
 * de la tabla empleados en la base de datos a objetos en la aplicación.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see IncidenciasDto
 */
@Entity
@Table(name = "empleados", schema = "m06Act02Tema03")
public class EmpleadosDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_empleado", nullable = false)
    private int idEmpleado;
    @NaturalId(mutable = true)
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;
    @Basic
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    @Basic
    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;
    @Basic
    @Column(name = "telefono_contacto", length = 20)
    private String telefonoContacto;
    @OneToMany(mappedBy = "empleadosByIdEmpleadoOrigen")
    private Collection<IncidenciasDto> incidenciasByIdEmpleadoOrigen;

    @OneToMany(mappedBy = "empleadosByIdEmpleadoOrigen")
    private Collection<IncidenciasDto> incidenciasByIdEmpleadoDestino;

    public EmpleadosDto() {}

    public EmpleadosDto(String nombreUsuario, String contrasena, String nombreCompleto, String telefonoContacto) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
    }
    public EmpleadosDto(int idEmpleado, String nombreUsuario, String contrasena, String nombreCompleto, String telefonoContacto) {
        this.idEmpleado = idEmpleado;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
    }

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

    public Collection<IncidenciasDto> getIncidenciasByIdEmpleadoOrigen() {
        return incidenciasByIdEmpleadoOrigen;
    }

    public void setIncidenciasByIdEmpleadoOrigen(Collection<IncidenciasDto> incidenciasByIdEmpleadoOrigen) {
        this.incidenciasByIdEmpleadoOrigen = incidenciasByIdEmpleadoOrigen;
    }
    public Collection<IncidenciasDto> getIncidenciasByIdEmpleadoDestino() {
        return incidenciasByIdEmpleadoOrigen;
    }

    public void setIncidenciasByIdEmpleadoDestino(Collection<IncidenciasDto> incidenciasByIdEmpleadoOrigen) {
        this.incidenciasByIdEmpleadoOrigen = incidenciasByIdEmpleadoOrigen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadosDto that = (EmpleadosDto) o;

        if (getIdEmpleado() != that.getIdEmpleado()) return false;
        if (!getNombreUsuario().equals(that.getNombreUsuario())) return false;
        if (!getContrasena().equals(that.getContrasena())) return false;
        if (!getNombreCompleto().equals(that.getNombreCompleto())) return false;
        return  (getTelefonoContacto().equals(that.getTelefonoContacto()));
    }

    @Override
    public int hashCode() {
        int result = getIdEmpleado();
        result = 31 * result + getNombreUsuario().hashCode();
        result = 31 * result + getContrasena().hashCode();
        result = 31 * result + getNombreCompleto().hashCode();
        result = 31 * result + getTelefonoContacto().hashCode();
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
