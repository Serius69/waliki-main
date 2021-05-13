package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.ContratoDto;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class DireccionDao {

    private DataSource dataSource;

    public ContratoDto findDireccionById(Integer direccionId) {
        ContratoDto result = new ContratoDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT  *" +
                            "FROM direccion di " +
                            "JOIN persona pe  ON pe.id_direccion_fk = di.id_direccion " +
                            "JOIN usuario us  ON us.id_persona_fk = pe.id_persona " +
                            "  WHERE pe.id_direccion_fk = " + direccionId +" " +
                            "GROUP BY pe.id_persona;" +
                            "     ");

            if (rs.next()) {
                result.contratoId = rs.getInt("id_contrato");
                result.contrato = rs.getString("contrato");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
