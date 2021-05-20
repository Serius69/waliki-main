package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ProyectoFinalizadoDto {

    private Integer proyectoId;
    private String nombreProyecto;
    private Double montoFinalRecaudado;
    private String fechaInicio;
    private String fechaFin;

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
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

    public Double getMontoFinalRecaudado() {
        return montoFinalRecaudado;
    }

    public void setMontoFinalRecaudado(Double montoFinalRecaudado) {
        this.montoFinalRecaudado = montoFinalRecaudado;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

}
