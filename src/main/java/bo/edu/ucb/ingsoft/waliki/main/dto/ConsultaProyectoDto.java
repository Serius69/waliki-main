package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ConsultaProyectoDto {

    private Integer proyectoId;
    private String nombreProyecto;
    private String descripcion;
    private Double montoRecaudar;
    private String estado;

    public ConsultaProyectoDto(){}

    public Integer getProyectoId() {      return proyectoId;    }

    public void setProyectoId(Integer proyectoId) {   this.proyectoId = proyectoId;    }

    public String getNombreProyecto() {     return nombreProyecto; }

    public void setNombreProyecto(String nombreProyecto) {  this.nombreProyecto = nombreProyecto; }

    public String getDescripcion() {  return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getMontoRecaudar() { return montoRecaudar;  }

    public void setMontoRecaudar(Double montoRecaudar) { this.montoRecaudar = montoRecaudar; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) {   this.estado = estado;  }
}
