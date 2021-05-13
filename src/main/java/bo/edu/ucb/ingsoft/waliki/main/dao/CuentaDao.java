package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.ContratoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.CuentaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class CuentaDao {
    @Autowired
    private DataSource dataSource;

    public CuentaDto findCuentabyId(Integer cuentaId) {
        CuentaDto result = new CuentaDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT  id_cuenta, id_banco, numero_cuenta, tipo_cuenta, id_persona, nombre_persona, apellidos, id_direccion_fk, fecha_nacimiento, username, contrasena, correo_electronico, telefono, id_persona_fk, tipo_usuario " +
                            "FROM cuenta cu " +
                            "JOIN emprendedor em  ON em.id_emprendedor = cu.id_emprendedor " +
                            "JOIN usuario us  ON us.id_usuario = em.id_usuario " +
                            "JOIN persona pe  ON us.id_persona_fk = pe.id_persona " +
                            "  WHERE  = " + cuentaId +" " +
                            "GROUP BY pe.id_persona;" +
                            "     ");

            if (rs.next()) {
                result.cuentaId = rs.getInt("id_cuenta");
                result.numeroCuenta = rs.getString("numero_cuenta");
            } else { // si no hay valores de BBDD
                result = null;
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
