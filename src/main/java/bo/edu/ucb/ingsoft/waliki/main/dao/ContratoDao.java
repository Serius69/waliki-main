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
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            try (ResultSet rs = stmt.executeQuery(
                    String.format("SELECT id_contrato, contenido, tipo_contrato FROM contrato    WHERE id_contrato = %d GROUP BY id_contrato;     ", contratoId))) {

                if (rs.next()) {
                    result.contratoId = rs.getInt("id_contrato");
                    result.contrato = rs.getString("contenido");
                } else { // si no hay valores de BBDD
                    result.contratoId = 1;
                    result.contrato = "Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
