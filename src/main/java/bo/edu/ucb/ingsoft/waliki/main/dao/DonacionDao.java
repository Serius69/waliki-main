package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class DonacionDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;

    public DonacionDto crearDonacion (DonacionDto donacion) {
        donacion.setDonacionId(sequenceDao.getPrimaryKeyForTable("donacion"));
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO donacion VALUES (?,?,?,?,?,?) ");

            stmt.setInt(1, donacion.getDonacionId());
            stmt.setInt(2, donacion.getProyectoId());
            stmt.setInt(3, donacion.getDonadorId());
            stmt.setDouble(4,donacion.getMonto());
            stmt.setString(5,donacion.getHora());
            stmt.setString(6,donacion.getFecha_donacion());
            /*
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(String.format("INSERT INTO donacion VALUES ('%d', '%d', '%d', '%s', '%s', '%s') ",
                    donacion.donacionId,
                    donacion.proyectoId,
                    donacion.donadorId,
                    donacion.monto,
                    donacion.hora,
                    donacion.fecha_donacion));

             */
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqex) {
                    // No hacer nada intencionalemte;
                }
            }
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
                result.setDonacionId(rs.getInt("id_donacion"));
                result.setFecha_donacion(rs.getString("fecha_donaciop"));
                result.setMonto(rs.getDouble("monto"));
                result2.nombre = rs.getString("pe.nombre");
                result3.setFechaInicio(rs.getString("pr.nombre"));
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
