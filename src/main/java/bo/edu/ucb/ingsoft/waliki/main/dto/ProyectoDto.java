package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ProyectoDto {

    private Integer proyectoId;
    private String nombreProyecto;
    private String descripcion;
    private Double montoRecaudar;
    private Integer emprendedorId;
    private String horaInicio;
    private String horaFin;
    private Integer estadoId;
    private String fechaInicio;
    private String fechaFin;

    public ProyectoDto(){



    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public Integer getEmprendedorId() {
        return emprendedorId;
    }

    public void setEmprendedorId(Integer emprendedorId) {
        this.emprendedorId = emprendedorId;
    }

    public Double getMontoRecaudar() {
        return montoRecaudar;
    }

    public void setMontoRecaudar(Double montoRecaudar) {
        this.montoRecaudar = montoRecaudar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }
}
