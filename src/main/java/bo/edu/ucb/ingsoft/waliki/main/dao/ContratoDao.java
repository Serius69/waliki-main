package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class ContratoDao {
    @Autowired
    private DataSource dataSource;
    //Buscar un contrato por su id
    public ContratoDto findContratoById(Integer contratoId) {
        ContratoDto result = new ContratoDto();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT id_contrato, contenido, tipo_contrato FROM contrato WHERE id_contrato = ? GROUP BY id_contrato; " )
        )
        {   pstmt.setInt(1, contratoId );
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setContratoId(rs.getInt("id_contrato"));
                result.setContrato(rs.getString("contenido"));
                result.setTipoContrato(rs.getString("tipo_contrato"));
            } else { // si no hay valores de BBDD
                result.setContratoId(null);
                result.setContrato(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
