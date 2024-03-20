package model;

/**
 * Representa un empleado dentro del sistema. Contiene información sobre el empleado,
 * incluyendo su ID, nombre de usuario, contraseña, nombre completo, y teléfono de contacto.
 * Esta clase ofrece métodos para obtener y establecer estos valores, permitiendo
 * la manipulación y acceso a los datos del empleado.
 * <p>
 * Además, sobreescribe los métodos {@code toString}, {@code equals}, y {@code hashCode}
 * para facilitar la visualización y comparación de objetos {@code Empleado}.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 */
public class Empleado {
    private int idEmpleado;
    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;
    private String telefonoContacto;

    // Constructor por defecto
    public Empleado() {
    }

    // Constructor con todos los campos
    public Empleado(int idEmpleado, String nombreUsuario, String contrasena, String nombreCompleto, String telefonoContacto) {
        this.idEmpleado = idEmpleado;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
    }

    public Empleado(String nombreUsuario, String contrasena, String nombreCompleto, String telefonoContacto) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
    }

    // Getters y setters
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

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleado empleado = (Empleado) o;

        if (getIdEmpleado() != empleado.getIdEmpleado()) return false;
        if (getNombreUsuario() != null ? !getNombreUsuario().equals(empleado.getNombreUsuario()) : empleado.getNombreUsuario() != null)
            return false;
        if (getContrasena() != null ? !getContrasena().equals(empleado.getContrasena()) : empleado.getContrasena() != null)
            return false;
        if (getNombreCompleto() != null ? !getNombreCompleto().equals(empleado.getNombreCompleto()) : empleado.getNombreCompleto() != null)
            return false;
        return getTelefonoContacto() != null ? getTelefonoContacto().equals(empleado.getTelefonoContacto()) : empleado.getTelefonoContacto() == null;
    }

    @Override
    public int hashCode() {
        int result = getIdEmpleado();
        result = 31 * result + (getNombreUsuario() != null ? getNombreUsuario().hashCode() : 0);
        result = 31 * result + (getContrasena() != null ? getContrasena().hashCode() : 0);
        result = 31 * result + (getNombreCompleto() != null ? getNombreCompleto().hashCode() : 0);
        result = 31 * result + (getTelefonoContacto() != null ? getTelefonoContacto().hashCode() : 0);
        return result;
    }
}

