package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        donador.setDonadorId(sequenceDao.getPrimaryKeyForTable("donador"));

        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO donador VALUES (?,?,?) ")
                ) {
            pstmt.setInt(1, donador.getUsuarioId());
            pstmt.setInt(2, donador.getContratoId());
            pstmt.setInt(3, donador.getDonadorId());
            pstmt.setInt(4, donador.getDonadorId());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return donador;
    }

    public DonadorDto findDonadorByName(String nombreDonador) {
        DonadorDto result = new DonadorDto();
        ProyectoDto result2 = new ProyectoDto();
        PersonaDto result3 = new PersonaDto();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT nombre_proyecto, dn.monto " +
                        "FROM donador d " +
                        "JOIN proyecto pr ON d.id_donador= pr.id_proyecto " +
                        "JOIN donacion dn ON d.id_donador = dn.id_donacion " +
                        "JOIN usuario us ON us.id_usuario = d.id_usuario " +
                        "JOIN persona pe ON pe.id_persona = us.id_persona_fk " +
                        "WHERE  pe.nombre_persona = ? " +
                        "GROUP BY  pr.nombre_proyecto, dn.monto; ")
                )
        {   pstmt.setString(1, nombreDonador);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setDonadorId(rs.getInt("id_donador"));
                result2.setNombreProyecto(rs.getString("nombre_proyecto"));
                result.setNombrePersona(rs.getString("id_usuario"));
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
                result.setDonadorId(rs.getInt("id_persona"));
                result.setContratoId(rs.getInt("id_contrato"));
                result.setContratoId(rs.getInt("id_usuario"));
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
// Toma todos los donadores de la BD
    public List<ConsultaDto> findAllDonadores() {
        List<ConsultaDto> result = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            try (ResultSet rs = stmt.executeQuery(
                    "SELECT  nombre_persona, nombre_proyecto, monto " +
                            "FROM donador d " +
                            "JOIN proyecto pr  ON d.id_donador = pr.id_proyecto " +
                            "JOIN donacion dn  ON d.id_donador = dn.id_donacion " +
                            "JOIN usuario us  ON us.id_usuario = d.id_usuario " +
                            "JOIN persona pe  ON pe.id_persona = us.id_persona_fk " +
                            "GROUP BY  pe.nombre_persona , pr.nombre_proyecto, dn.monto;" +
                            " ")) {
                while (rs.next()) {
                    ConsultaDto consulta = new ConsultaDto();

                    consulta.nombrePersona = rs.getString("nombre_persona");
                    consulta.nombreProyecto = rs.getString("nombre_proyecto");
                    consulta.monto_donacion = rs.getDouble("monto");
                    result.add(consulta);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
