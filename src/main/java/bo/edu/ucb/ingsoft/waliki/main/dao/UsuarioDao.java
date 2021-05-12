package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.DonadorDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private DataSource dataSource;

    @Autowired
    private SequenceDao sequenceDao;

    public UsuarioDto crearUsuario (UsuarioDto usuario) {
        usuario.usuarioId = sequenceDao.getPrimaryKeyForTable("donador");
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("" +
                    "INSERT INTO donador VALUES ("
                    + usuario.usuarioId +", '"
                    + usuario.usuario +"', '"
                    + usuario.contrasena+"') ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuario;
    }

    public UsuarioDto findUsuarioByUsername(String donadorId) {
        UsuarioDto result = new UsuarioDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT pr.nombre, monto, SUM(monto)" +
                            "FROM donador d " +
                            "JOIN proyecto pr ON  d.id_donador= pr.id_proyecto " +
                            "JOIN donacion dn ON d.id_donador = dn.id_donacion " +
                            "JOIN usuario us ON us.id_usuario = d.id_usuario " +
                            "JOIN persona pe ON pe.id_persona = us.id_persona" +

                            "  WHERE id_persona = " + donadorId +" " +
                            "GROUP BY pe.nombre , pr.nombre, dn.monto;" +
                            "     ");

            if (rs.next()) {
                result.usuarioId = rs.getInt("id_persona");
                result.usuario = rs.getString("usuario");
                result.codigo_verificacion = rs.getString("codigo_verficacion");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public UsuarioDto findUsuarioById(Integer usuarioId) {
        UsuarioDto result = new UsuarioDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT pr.nombre, monto, SUM(monto)" +
                            "FROM donador d " +
                            "JOIN proyecto pr ON  d.id_donador= pr.id_proyecto " +
                            "JOIN donacion dn ON d.id_donador = dn.id_donacion " +
                            "JOIN usuario us ON us.id_usuario = d.id_usuario " +
                            "JOIN persona pe ON pe.id_persona = us.id_persona" +

                            "  WHERE id_persona = " + usuarioId +" " +
                            "GROUP BY pe.nombre , pr.nombre, dn.monto;" +
                            "     ");

            if (rs.next()) {
                result.usuarioId = rs.getInt("id_persona");
                result.usuario = rs.getString("id_contrato");
                result.codigo_verificacion = rs.getString("id_usuario");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}
