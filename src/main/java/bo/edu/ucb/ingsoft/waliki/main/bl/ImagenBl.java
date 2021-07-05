package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import bo.edu.ucb.ingsoft.waliki.main.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public class ImagenBl {
    @Autowired
    ImagenDao imagenDAO;
    public ImagenDto NewImagen(ImagenDto imagenDTO)throws SQLException{
        return imagenDAO.NewImagen(imagenDTO);
    }
    public List<ImagenDto> findAllImagen()throws SQLException{
        return imagenDAO.findAllImagen();
    }
    public ImagenDto findImagenById(Integer idImagen)throws SQLException{
        return imagenDAO.findImagenById(idImagen);
    }
    public ImagenDto DeleteImagen(Integer idImagen)throws SQLException{
        return imagenDAO.DeleteImagen(idImagen);
    }
    public ImagenDto UpdateImagen(ImagenDto imagenDTO)throws SQLException{
        return imagenDAO.UpdateImagen(imagenDTO);
    }
}
