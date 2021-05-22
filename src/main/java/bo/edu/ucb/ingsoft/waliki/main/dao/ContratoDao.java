package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class ContratoDao {
    @Autowired
    private DataSource dataSource;

    public ContratoDto findContratoById(Integer contratoId) {
        ContratoDto result = new ContratoDto();
        try ( Connection conn = dataSource.getConnection()){

            Statement stmt = conn.createStatement();

            try (ResultSet rs = stmt.executeQuery(
                    String.format("SELECT id_contrato, contenido, tipo_contrato FROM contrato WHERE id_contrato = %d GROUP BY id_contrato;", contratoId))) {
                if (rs.next()) {
                    result.contratoId = rs.getInt("id_contrato");
                    result.contrato = rs.getString("contenido");
                    result.tipoContrato = rs.getString("tipo_contrato");
                } else { // si no hay valores de BBDD
                    result.contratoId = null;
                    result.contrato = null;
                }
                conn.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
