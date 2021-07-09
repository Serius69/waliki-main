package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ConsultaDonacionDto {

    private Integer donacionId;
    private String nombreUsuario;
    private String nombreProyecto;
    private Double montoDonacion;
    private String imagenProyecto;


    public Integer getDonacionId() {
        return donacionId;
    }

    public void setDonacionId(Integer donacionId) {
        this.donacionId = donacionId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Double getMontoDonacion() {
        return montoDonacion;
    }

    public void setMontoDonacion(Double montoDonacion) {
        this.montoDonacion = montoDonacion;
    }

    public String getImagenProyecto() {
        return imagenProyecto;
    }

    public void setImagenProyecto(String imagenProyecto) {
        this.imagenProyecto = imagenProyecto;
    }
}
