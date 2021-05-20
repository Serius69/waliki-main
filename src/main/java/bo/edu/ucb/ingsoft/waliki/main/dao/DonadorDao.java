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

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;

    public DonadorDto crearDonador (DonadorDto donador) {
        donador.setDonadorId(sequenceDao.getPrimaryKeyForTable("donador"));
        donador.setUsuarioId(sequenceDao.getPrimaryKeyForTable("usuario"));
        donador.setPersonaId(sequenceDao.getPrimaryKeyForTable("persona"));
        donador.setDireccionId(sequenceDao.getPrimaryKeyForTable("direccion"));

        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO direccion VALUES (?,?,?,?,?); INSERT INTO persona VALUES (?,?,?,?,?); INSERT INTO usuario VALUES (?,?,?,?,?,?,?); INSERT INTO donador VALUES (?,?,?); ")
                ) {
            //---Tabla direccion
            pstmt.setInt(1, donador.getDireccionId()); //id_direccion
            pstmt.setString(2, donador.getDireccion()); //zona
            pstmt.setString(3, donador.getDireccion()); //calle
            pstmt.setString(4, "La Paz"); //ciudad
            pstmt.setString(5, "La Paz"); //departamento
            //---Tabla persona
            pstmt.setInt(6, donador.getPersonaId());// ------
            pstmt.setInt(7, donador.getPersonaId());// nombre
            pstmt.setInt(8, donador.getPersonaId());// apellidos
            pstmt.setInt(9, donador.getPersonaId());// id_direccion_fk
            pstmt.setString(10, "mm/dd/yyyy");// fecha_nacimiento
            //--- Tabla Usuario
            pstmt.setInt(11, donador.getUsuarioId()); // id usuario
            pstmt.setString(12, donador.getNombreUsuario()); //nombre usuario
            pstmt.setString(13, donador.getContrasena()); // contrasenia
            pstmt.setString(14, donador.getCorreoElectronico()); //correo electronico
            pstmt.setInt(15, donador.getNumeroTelefono()); // numero telefono
            pstmt.setInt(16, donador.getPersonaId()); // id_persona_fk
            pstmt.setString(17, "Donador"); // tipo_usuario
            //--Tabla donador
            pstmt.setInt(18, donador.getDonadorId());
            pstmt.setInt(19, donador.getContratoId());
            pstmt.setInt(20, donador.getUsuarioId());


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


    // Toma todos los donadores de la BD
    public List<ConsultaDto> findAllDonadores() {
        List<ConsultaDto> result = new ArrayList<>();
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT  nombre_persona, nombre_proyecto, monto " +
                                "FROM donador d " +
                                "JOIN proyecto pr  ON d.id_donador = pr.id_proyecto " +
                                "JOIN donacion dn  ON d.id_donador = dn.id_donacion " +
                                "JOIN usuario us ON us.id_usuario = d.id_usuario " +
                                "JOIN persona pe  ON pe.id_persona = us.id_persona_fk " +
                                "GROUP BY  pe.nombre_persona , pr.nombre_proyecto, dn.monto; ")
                )
        {  ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    ConsultaDto consulta = new ConsultaDto();

                    consulta.nombrePersona = rs.getString("nombre_persona");
                    consulta.nombreProyecto = rs.getString("nombre_proyecto");
                    consulta.monto_donacion = rs.getDouble("monto");
                    result.add(consulta);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return result;
    }
}
