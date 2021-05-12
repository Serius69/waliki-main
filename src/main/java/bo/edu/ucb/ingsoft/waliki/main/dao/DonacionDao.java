package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import bo.edu.ucb.ingsoft.waliki.main.models.Donacion;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DonacionDao {

    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;

    public DonacionDto crearDonacion (DonacionDto donacion) {
        donacion.donacionId = sequenceDao.getPrimaryKeyForTable("donacion");
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("" +
                    "INSERT INTO donador VALUES ("
                    + donacion.donacionId +", '"
                    + donacion.fecha +"', '"
                    + donacion.monto +"', '"
                    + donacion.proyectoId +"', '"
                    + donacion.donadorId+"') ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return donacion;
    }
    public DonacionDto findDonacionById(Integer donacionId) {
        DonacionDto result = new DonacionDto();
        PersonaDto result2 = new PersonaDto();
        ProyectoDto result3 = new ProyectoDto();
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

                            "  WHERE id_donacion = " + donacionId +" " +
                            "GROUP BY pe.nombre , pr.nombre, dn.monto;" +
                            "     ");

            if (rs.next()) {
                result.donacionId = rs.getInt("id_persona");
                result.fecha = rs.getString("fecha");
                result.monto = rs.getString("monto");
                result2.nombre = rs.getString("pe.nombre");
                result3.nombre= rs.getString("pr.nombre");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
