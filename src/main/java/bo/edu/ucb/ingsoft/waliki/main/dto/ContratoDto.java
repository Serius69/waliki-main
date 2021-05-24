package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ContratoDto {
    private Integer contratoId;
    private String contrato;
    private String tipoContrato;

    public ContratoDto(){

    }

    public Integer getContratoId() {
        return contratoId;
    }

    public void setContratoId(Integer contratoId) {
        this.contratoId = contratoId;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
}
