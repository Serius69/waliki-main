package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonadorDao {

    private final DataSource dataSource;
    private final SequenceDao sequenceDao;

    public DonadorDao(DataSource dataSource, SequenceDao sequenceDao) {
        this.dataSource = dataSource;
        this.sequenceDao = sequenceDao;
    }

    public DonadorDto crearDonador (DonadorDto donadorDto) {
        donadorDto.setDonadorId(sequenceDao.getPrimaryKeyForTable("donador"));
        donadorDto.setUsuarioId(sequenceDao.getPrimaryKeyForTable("usuario"));
        donadorDto.setPersonaId(sequenceDao.getPrimaryKeyForTable("persona"));
        donadorDto.setDireccionId(sequenceDao.getPrimaryKeyForTable("direccion"));


        donadorDto.setContratoId(1);
        donadorDto.setContrato("borrado");
        donadorDto.setNombrePersona("----");

        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("" +
                    "INSERT INTO direccion (id_direccion, zona, calle, ciudad, departamento) VALUES (?,?,?,?,?); " +
                    "INSERT INTO persona (id_persona, nombre_persona, apellidos, id_direccion_fk, fecha_nacimiento) VALUES (?,?,?,?,?); " +
                    "INSERT INTO usuario (id_usuario, username, contrasena, correo_electronico, telefono, id_persona_fk, tipo_usuario) VALUES (?,?,?,?,?,?,?); " +
                    "INSERT INTO donador (id_donador, id_contrato, id_usuario) VALUES (?,?,?); ")
                ) {
            //---Tabla direccion
            pstmt.setInt(1, donadorDto.getDireccionId()); //id_direccion
            pstmt.setString(2, "Zona Sur"); //zona
            pstmt.setString(3, donadorDto.getDireccion()); //calle
            pstmt.setString(4, "La Paz"); //ciudad
            pstmt.setString(5, "La Paz"); //departamento
            //---Tabla persona
            pstmt.setInt(6, donadorDto.getPersonaId());// id_persona
            pstmt.setString(7, donadorDto.getNombrePersona());// nombre
            pstmt.setString(8, donadorDto.getNombrePersona());// apellidos
            pstmt.setInt(9, donadorDto.getDireccionId());// id_direccion_fk
            pstmt.setString(10, "mm/dd/yyyy");// fecha_nacimiento
            //--- Tabla Usuario
            pstmt.setInt(11, donadorDto.getUsuarioId()); // id usuario
            pstmt.setString(12, donadorDto.getNombreUsuario()); //nombre usuario
            pstmt.setString(13, donadorDto.getContrasena()); // contrasenia
            pstmt.setString(14, donadorDto.getCorreoElectronico()); //correo electronico
            pstmt.setInt(15, donadorDto.getNumeroTelefono()); // numero telefono
            pstmt.setInt(16, donadorDto.getPersonaId()); // id_persona_fk
            pstmt.setString(17, "Donador"); // tipo_usuario
            //--Tabla donador
            pstmt.setInt(18, donadorDto.getDonadorId());
            pstmt.setInt(19, donadorDto.getContratoId());
            pstmt.setInt(20, donadorDto.getUsuarioId());
            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return donadorDto;
    }

    public ConsultaDonadorDto findDonadorByName(Integer idDonador) {
        ConsultaDonadorDto result = new ConsultaDonadorDto();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT d.id_donador, nombre_persona, nombre_proyecto, monto " +
                        "FROM proyecto pr " +
                        "JOIN donador d ON d.id_donador= pr.id_proyecto " +
                        "JOIN donacion dn ON d.id_donador = dn.id_donacion " +
                        "JOIN usuario us ON us.id_usuario = d.id_usuario " +
                        "JOIN persona pe ON pe.id_persona = us.id_persona_fk " +
                        "WHERE d.id_donador = ? " + // falla con el signo de ?
                        "GROUP BY pe.nombre_persona, pr.nombre_proyecto, dn.monto;")
                )
        {   pstmt.setInt(1, idDonador );
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setDonadorId(rs.getInt("id_donador"));
                result.setNombrePersona(rs.getString("nompre_persona"));
                result.setNombreProyecto(rs.getString("nombre_proyecto"));
                result.setMonto_donacion(rs.getDouble("monto"));
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    // Toma todos los donadores de la BD
    public List<ConsultaDonadorDto> findAllDonadores() {
        List<ConsultaDonadorDto> result = new ArrayList<>();
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT d.id_donador, nombre_persona, nombre_proyecto, monto " +
                                "FROM donador d " +
                                "JOIN donacion dn  ON d.id_donador = dn.id_donador " +
                                "JOIN proyecto pr  ON dn.id_proyecto = pr.id_proyecto " +
                                "JOIN usuario us ON us.id_usuario = d.id_usuario " +
                                "JOIN persona pe  ON pe.id_persona = us.id_persona_fk " +
                                "WHERE d.id_donador = dn.id_donador " +
                                "AND us.id_usuario = d.id_usuario "+
                                "AND us.id_persona_fk = pe.id_persona "+
                                "GROUP BY  d.id_donador, pe.nombre_persona , pr.nombre_proyecto, dn.monto; ")
                )
        {  ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    ConsultaDonadorDto consulta = new ConsultaDonadorDto();
                    consulta.setDonadorId(rs.getInt("id_donador"));
                    consulta.setNombrePersona(rs.getString("nombre_persona"));
                    consulta.setNombreProyecto(rs.getString("nombre_proyecto"));
                    consulta.setMonto_donacion(rs.getDouble("monto"));
                    result.add(consulta);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return result;
    }
}
