package bo.edu.ucb.ingsoft.waliki.main.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class SequenceDao {

    @Autowired
    private DataSource dataSource;
    public int getPrimaryKeyForTable(String nombreTabla) {
        String nombreSecuencia = nombreTabla.toLowerCase() + "_" + nombreTabla.toLowerCase() + "_id_seq";
        // SELECT nextval('persona_persona_id_seq');
        int resultado = 0;
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nextval('"+ nombreSecuencia +"'::regclass)");  //FIXME SQL INJECTION !!!!!
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
}