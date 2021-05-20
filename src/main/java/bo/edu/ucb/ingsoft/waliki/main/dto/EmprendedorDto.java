package bo.edu.ucb.ingsoft.waliki.main.dto;

public class EmprendedorDto {

    private Integer emprendedorId;
    private Integer tipoemprendimientoId;
    private Integer contratoId;
    private Integer usuarioId;


    public EmprendedorDto(){

    }
    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getContratoId() {
        return contratoId;
    }

    public void setContratoId(Integer contratoId) {
        this.contratoId = contratoId;
    }

    public Integer getTipoemprendimientoId() {
        return tipoemprendimientoId;
    }

    public void setTipoemprendimientoId(Integer tipoemprendimientoId) {
        this.tipoemprendimientoId = tipoemprendimientoId;
    }

    public Integer getEmprendedorId() {
        return emprendedorId;
    }

    public void setEmprendedorId(Integer emprendedorId) {
        this.emprendedorId = emprendedorId;
    }
}
