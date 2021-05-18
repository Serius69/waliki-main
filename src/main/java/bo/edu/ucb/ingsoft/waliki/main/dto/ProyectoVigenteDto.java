package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ProyectoVigenteDto {

    private Integer proyectoId;
    private String nombreProyecto;
    private String imagen;
    private Double montoRQ;
    private String tiempoRestante;

    public  ProyectoVigenteDto(){


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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
