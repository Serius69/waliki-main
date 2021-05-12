package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.ContratoDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ContratoDao {
    @Autowired
    private DataSource dataSource;

    public ContratoDto findContratoById(Integer contratoId) {
        ContratoDto result = new ContratoDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT contrato " +
                            "FROM contrato co " +
                            "  WHERE co.id_contrato = " + contratoId +" " +
                            "GROUP BY co.id_contrato;" +
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
