package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ProyectoEnProcesoDto {

    private Integer proyectoId;
    private String nombreProyecto;
    private String fechaInicio;
    private String fechaFinal;
    private Double proceso;

    public  ProyectoEnProcesoDto(){

    }
    public Double getProceso() {
        return proceso;
    }

    public void setProceso(Double proceso) {
        this.proceso = proceso;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
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
