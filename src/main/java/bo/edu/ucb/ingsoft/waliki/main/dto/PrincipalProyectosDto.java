package bo.edu.ucb.ingsoft.waliki.main.dto;

public class PrincipalProyectosDto {
    private Integer proyectoId;
    private Double monto_recaudar;
    private String descripcion;
    private String proyectoNombre;

    public PrincipalProyectosDto(){}

    public String getProyectoNombre() {
        return proyectoNombre;
    }

    public void setProyectoNombre(String proyectoNombre) {
        this.proyectoNombre = proyectoNombre;
    }

    public Integer getProyectoId() {    return proyectoId;  }

    public void setProyectoId(Integer proyectoId) {     this.proyectoId = proyectoId;   }

    public Double getMonto_recaudar() {      return monto_recaudar;   }

    public void setMonto_recaudar(Double monto_recaudar) {      this.monto_recaudar = monto_recaudar;  }

    public String getDescripcion() {      return descripcion;   }

    public void setDescripcion(String descripcion) {     this.descripcion = descripcion;  }
}
