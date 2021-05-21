package bo.edu.ucb.ingsoft.waliki.main.dto;

public class DonacionDto {
    private Integer donacionId;
    private Integer proyectoId;
    private Integer donadorId;
    private Double monto;
    private String hora;
    private String fecha_donacion;

    public DonacionDto(){
    }

    public Integer getDonacionId() {
        return donacionId;
    }

    public void setDonacionId(Integer donacionId) {
        this.donacionId = donacionId;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Integer getDonadorId() {
        return donadorId;
    }

    public void setDonadorId(Integer donadorId) {
        this.donadorId = donadorId;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha_donacion() {
        return fecha_donacion;
    }

    public void setFecha_donacion(String fecha_donacion) {
        this.fecha_donacion = fecha_donacion;
    }
}
