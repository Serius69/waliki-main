package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ConsultaDonadorDto {

    private Integer donadorId;
    private String nombrePersona;
    private String nombreProyecto;
    private Double monto_donacion;

    public ConsultaDonadorDto() {

    }

    public Integer getDonadorId() {        return donadorId;    }

    public void setDonadorId(Integer donadorId) {        this.donadorId = donadorId;    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Double getMonto_donacion() {
        return monto_donacion;
    }

    public void setMonto_donacion(Double monto_donacion) {
        this.monto_donacion = monto_donacion;
    }
}
