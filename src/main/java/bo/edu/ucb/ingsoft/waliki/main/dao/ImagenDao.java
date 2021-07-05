package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImagenDao {
    //Se inicia SequenceDao para auto incremento
    @Autowired
    private SequenceDao sequenceDao;
    //Conexion a base de datos
    @Autowired
    public DataSource dataSource;
    /**
     * La funcion se encargara de almacenar una imagen en la tabla imagen
     */
    public ImagenDto NewImagen(ImagenDto imagenDTO)throws SQLException{
        imagenDTO.setIdImagen(sequenceDao.getPrimaryKeyForTable("imagen"));
        //Connection con=null;
        try (Connection con=dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO imagen(id_imagen,src_imagen,img) VALUES (?,?,?)")){
            pstmt.setInt(1,imagenDTO.getIdImagen());
            pstmt.setString(2, imagenDTO.getSrcImagen());
            pstmt.setString(3,imagenDTO.getImg());
            pstmt.executeUpdate();
            pstmt.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return imagenDTO;
    }
    /**
     * la siguiente funcion se encarga de llamar todos los datos de la tabla imagen
     */
    public List<ImagenDto> findAllImagen()throws SQLException{
        List<ImagenDto> result =new ArrayList<>();
        try{Connection conn = dataSource.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT id_imagen,src_imagen,img from imagen");
            /**try(Connection con=dataSource.getConnection();
             PreparedStatement pstmt=con.prepareStatement("SELECT id_imagen, src_imagen, img from imagen");)
             {**/
            //ResultSet resultSet=pstmt.executeQuery();
            while (resultSet.next()){
                ImagenDto imagenDTO = new ImagenDto();
                imagenDTO.setIdImagen(resultSet.getInt("id_imagen"));
                imagenDTO.setSrcImagen(resultSet.getString("src_imagen"));
                imagenDTO.setImg(resultSet.getString("img"));
                result.add(imagenDTO);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    /**
     * La siguiente funcion se encarga de llamar por id los datos de la tabla
     */
    public ImagenDto findImagenById(Integer idImagen)throws SQLException{
        ImagenDto imagenDTO = new ImagenDto();
        try (Connection conn= dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT id_imagen, src_imagen,img FROM imagen where id_imagen=?");)
        {
            pstmt.setInt(1,idImagen);
            ResultSet resultSet=pstmt.executeQuery();
            if(resultSet.next()){
                imagenDTO.setIdImagen(resultSet.getInt("id_imagen"));
                imagenDTO.setSrcImagen(resultSet.getString("src_imagen"));
                imagenDTO.setImg(resultSet.getString("img"));

            }else {
                resultSet=null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return imagenDTO;
    }
    /**
     * La siguiente funcion se encarga de eliminar un elemento de la lista buscandolo por id.
     */
    public ImagenDto DeleteImagen(Integer idImagen)throws SQLException{
        ImagenDto imagenDTO=new ImagenDto();
        try(Connection con= dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("delete from imagen where id_imagen=?"))
        {
            pstmt.setInt(1,idImagen);
            ResultSet resultSet= pstmt.executeQuery();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return imagenDTO;
    }
    /**
     * La siguiente funcion se encarga de la actualizacion de los datos de la tabla en busqueda de id .
     */
    public ImagenDto UpdateImagen(ImagenDto imagenDTO)throws SQLException{
        try (Connection con=dataSource.getConnection();
             PreparedStatement pstmt =con.prepareStatement("UPDATE imagen SET src_imagen=?,img=? where id_imagen=?")) {
            pstmt.setInt(3, imagenDTO.getIdImagen());
            pstmt.setString(1, imagenDTO.getSrcImagen());
            pstmt.setString(2, imagenDTO.getImg());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return imagenDTO;
    }
}
