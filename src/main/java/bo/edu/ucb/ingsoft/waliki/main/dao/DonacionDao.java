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

    public DonacionDto crearDonacion (DonacionDto donacionDto) {
        donacionDto.setDonacionId(sequenceDao.getPrimaryKeyForTable("donacion"));
        try (Connection conn = dataSource.getConnection()) //cerrado de conexion
        {
            PreparedStatement stmt = conn.prepareStatement("" +
                    "INSERT INTO donacion (id_donacion, id_proyecto, id_donador, monto, hora, fecha_donacion) " +
                    "VALUES (?,?,?,?,?,?) ");

            stmt.setInt(1, donacionDto.getDonacionId());
            stmt.setInt(2, donacionDto.getProyectoId());
            stmt.setInt(3, donacionDto.getDonadorId());
            stmt.setDouble(4, donacionDto.getMonto());
            stmt.setString(5, donacionDto.getHora());
            stmt.setString(6, donacionDto.getFecha_donacion());
            stmt.executeQuery();
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
        }
        // No hacer nada intencionalemte;
        return donacionDto;
    }
}
