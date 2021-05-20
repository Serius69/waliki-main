package bo.edu.ucb.ingsoft.waliki.main.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class SequenceDao {

    @Autowired
    private DataSource dataSource;
    public int getPrimaryKeyForTable(String nombreTabla) {
        //Select nextval(donador_id_donador_seq.)proyecto_id_proyecto_seq
        String nombreSecuencia = nombreTabla.toLowerCase() + "_id_" + nombreTabla.toLowerCase() + "_seq";
        // SELECT nextval('persona_persona_id_seq');
        int resultado = 0;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT nextval(?)")) {
            pstmt.setString(1, nombreSecuencia);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
}