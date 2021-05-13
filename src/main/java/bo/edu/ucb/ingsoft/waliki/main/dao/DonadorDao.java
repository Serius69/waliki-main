package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.DonadorDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.PersonaDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonadorDao {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;

    public DonadorDto crearDonador (DonadorDto donador) {
        donador.donadorId = sequenceDao.getPrimaryKeyForTable("donador");
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("" +
                    "INSERT INTO donador VALUES ("
                    + donador.donadorId +", '"
                    + donador.contratoId +"', '"
                    + donador.usuarioId+"') ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return donador;
    }

    public DonadorDto findDonadorByName(String nombreDonador, String apellidoDonador) {
        DonadorDto result = new DonadorDto();
        ProyectoDto result2 = new ProyectoDto();
        PersonaDto result3 = new PersonaDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT nombre_proyecto, dn.monto " +
                            "FROM donador d " +
                            "JOIN proyecto pr ON  d.id_donador= pr.id_proyecto " +
                            "JOIN donacion dn ON d.id_donador = dn.id_donacion " +
                            "JOIN usuario us ON us.id_usuario = d.id_usuario " +
                            "JOIN persona pe ON pe.id_persona = us.id_persona_fk" +

                            "  WHERE  pe.nombre_persona = " + nombreDonador +" " +
                            " AND pe.apellidos =  " +apellidoDonador +" " +
                            "GROUP BY  pr.nombre_proyecto, dn.monto;" +
                            "     ");

            if (rs.next()) {
                result.donadorId = rs.getInt("id_donador");
                result2.nombreProyecto = rs.getString("nombre_proyecto");
                result3.nombre = rs.getString("id_usuario");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public DonadorDto findDonadorById(Integer donadorId) {
        DonadorDto result = new DonadorDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT nombre_proyecto, dn.monto " +
                            "FROM donador d " +
                            "JOIN proyecto pr ON  d.id_donador= pr.id_proyecto " +
                            "JOIN donacion dn ON d.id_donador = dn.id_donacion " +
                            "JOIN usuario us ON us.id_usuario = d.id_usuario " +
                            "JOIN persona pe ON pe.id_persona = us.id_persona_fk" +

                    "  WHERE d.id_donador = " + donadorId +" " +
                            "GROUP BY  pr.nombre_proyecto, dn.monto;" +
                            "     ");

            if (rs.next()) {
                result.donadorId = rs.getInt("id_persona");
                result.contratoId = rs.getInt("id_contrato");
                result.usuarioId = rs.getInt("id_usuario");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<DonadorDto> findAllDonadores() {
        List<DonadorDto> result = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * " +
                            "FROM donador d " +
                            "");
            while (rs.next()) {
                DonadorDto donador = new DonadorDto();
                donador.donadorId = rs.getInt("id_donador");
                donador.contratoId = rs.getInt("id_contrato");
                donador.usuarioId = rs.getInt("id_usuario");
                result.add(donador);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
