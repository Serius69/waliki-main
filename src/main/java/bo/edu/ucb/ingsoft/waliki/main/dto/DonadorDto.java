package bo.edu.ucb.ingsoft.waliki.main.dto;

public class DonadorDto {
    private Integer donadorId;
    private String nombreUsuario;
    private Integer contratoId;
    private String contrato;
    private Integer usuarioId;
    private String nombrePersona;

    public DonadorDto(){

    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Integer getContratoId() {
        return contratoId;
    }

    public void setContratoId(Integer contratoId) {
        this.contratoId = contratoId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getDonadorId() {
        return donadorId;
    }

    public void setDonadorId(Integer donadorId) {
        this.donadorId = donadorId;
    }
}
