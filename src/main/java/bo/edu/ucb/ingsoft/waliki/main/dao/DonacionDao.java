package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import bo.edu.ucb.ingsoft.waliki.main.models.Donacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class DonacionDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;

    public DonacionDto crearDonacion (DonacionDto donacion) {
        donacion.donacionId = sequenceDao.getPrimaryKeyForTable("donacion");
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(String.format("INSERT INTO donacion VALUES ('%d', '%d', '%d', '%s', '%s', '%s') ", donacion.donacionId, donacion.proyectoId, donacion.donadorId, donacion.monto, donacion.hora, donacion.fecha_donacion));
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
                    "SELECT pr.nombre_proyecto, d.monto " +
                            "FROM donacion d " +
                            "JOIN proyecto pr ON  d.id_donador= pr.id_proyecto " +
                            "JOIN donacion dn ON d.id_donador = dn.id_donacion " +
                            "JOIN usuario us ON us.id_usuario = d.id_donador " +
                            "JOIN persona pe ON pe.id_persona = us.id_persona_fk" +

                            "  WHERE d.id_donacion = " + donacionId +" " +
                            "GROUP BY pr.nombre_proyecto, dn.monto;" +
                            "     ");

            if (rs.next()) {
                result.donacionId = rs.getInt("id_donacion");
                result.fecha_donacion = rs.getString("fecha_donaciop");
                result.monto = rs.getDouble("monto");
                result2.nombre = rs.getString("pe.nombre");
                result3.fechaInicio= rs.getString("pr.nombre");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
