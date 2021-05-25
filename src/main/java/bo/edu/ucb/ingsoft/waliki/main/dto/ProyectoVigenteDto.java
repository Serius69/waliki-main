package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ProyectoVigenteDto {
    private Integer proyectoId;
    private String nombreProyecto;
    private Double montoRQ;
    private String tiempoRestante;

    public  ProyectoVigenteDto(){    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(String tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public Double getMontoRQ() {
        return montoRQ;
    }

    public void setMontoRQ(Double montoRQ) {
        this.montoRQ = montoRQ;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }
}
