package bo.edu.ucb.ingsoft.waliki.main.dto;

public class CreateEmprendedorDto {
    private Integer emprendedorId;
    private String nombre;
    private String apellidos;
    private String fecha_nacimiento;
    //private Integer carnet;
    private Integer nomeroTelefono;
    private String direccion;
    private String correoElectronico;
    private Integer tipoemprendimientoId;
    private String contrasenia;


    public CreateEmprendedorDto(){

    }

    public Integer getEmprendedorId() {
        return emprendedorId;
    }

    public void setEmprendedorId(Integer emprendedorId) {
        this.emprendedorId = emprendedorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Integer getNomeroTelefono() {
        return nomeroTelefono;
    }

    public void setNomeroTelefono(Integer nomeroTelefono) {
        this.nomeroTelefono = nomeroTelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Integer getTipoemprendimientoId() {
        return tipoemprendimientoId;
    }

    public void setTipoemprendimientoId(Integer tipoemprendimientoId) {
        this.tipoemprendimientoId = tipoemprendimientoId;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
