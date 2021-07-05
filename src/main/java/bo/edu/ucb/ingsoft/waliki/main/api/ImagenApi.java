package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.*;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

public class ImagenApi {
    @Autowired
    private ImagenBl gestionImagenBl;
    @GetMapping(path = "/imagen")
    public ResponseDto findAllImagen()throws SQLException{
        return new ResponseDto(true,gestionImagenBl.findAllImagen(),"Exito");
    }
    @GetMapping(path = "/imagen/{idImagen}")
    public ResponseDto findImagenById(@PathVariable Integer idImagen)throws SQLException{
        ImagenDto imagenDTO=gestionImagenBl.findImagenById(idImagen);
        if(imagenDTO!=null){
            return new ResponseDto(true,imagenDTO,"Exito");
        }else{
            return new ResponseDto(false,null,"No encontrado");
        }
    }
    @PostMapping(path = "/imagen")
    public ResponseDto NewImagen(@RequestBody ImagenDto imagenDTO)throws SQLException{
        ImagenDto imagenDTO1=gestionImagenBl.NewImagen(imagenDTO);
        if(imagenDTO1.getSrcImagen()==null || imagenDTO1.getSrcImagen().trim().equals("")){
            return new ResponseDto(false,null,null);
        }
        if (imagenDTO1.getImg()==null || imagenDTO1.getImg().trim().equals("")){
            return new ResponseDto(false,null,null);
        }
        return new ResponseDto(true,imagenDTO1,"Exito");
    }
    @DeleteMapping(path = "/imagen/{idImagen}")
    public ResponseDto DeleteImagen(@PathVariable Integer idImagen)throws SQLException{
        ImagenDto imagenDTO = gestionImagenBl.DeleteImagen(idImagen);
        if(imagenDTO!=null){
            return new ResponseDto(true,imagenDTO,"Exito");
        }else {
            return new ResponseDto(false,null,null);
        }
    }
    @PutMapping(path = "/imagen")
    public ResponseDto UpdateImagen(@RequestBody ImagenDto imagenDTO) throws SQLException {
        ImagenDto imagenDTO1 =gestionImagenBl.UpdateImagen(imagenDTO);
        if(imagenDTO1.getSrcImagen() ==null || imagenDTO1.getSrcImagen().trim().equals("")){
            return new ResponseDto(false,null,null);
        }
        if(imagenDTO1.getImg()==null || imagenDTO1.getImg().trim().equals("")){
            return new ResponseDto(false,null,null);
        }
        return new ResponseDto(true,imagenDTO1,"");
    }
}
