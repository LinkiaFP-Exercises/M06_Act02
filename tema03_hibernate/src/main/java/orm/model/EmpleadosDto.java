package orm.model;

import jakarta.persistence.*;

import java.util.Collection;

@SuppressWarnings("unused")
@Entity
@Table(name = "empleados", schema = "m06Act02Tema03")
public class EmpleadosDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_empleado", nullable = false)
    private int idEmpleado;
    @Basic
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

}
