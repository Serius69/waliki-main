package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ImagenDto {
    private Integer idImagen;
    private String SrcImagen;
    private String Img;
    public ImagenDto(){

    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String getSrcImagen() {
        return SrcImagen;
    }

    public void setSrcImagen(String srcImagen) {
        SrcImagen = srcImagen;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}
