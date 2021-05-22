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
public class EmprendedorDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SequenceDao sequenceDao;

    public CreateEmprendedorDto crearEmprendedor (CreateEmprendedorDto emprendedor) {
        emprendedor.setEmprendedorId(sequenceDao.getPrimaryKeyForTable("emprendedor"));
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt  = conn.prepareStatement("INSERT INTO emprendedor VALUES (?,?,?,?,?) ")
                ) {

            //---Tabla direccion
            //pstmt.setInt(1, donadorDto.getDireccionId()); //id_direccion
            pstmt.setString(2, "central"); //zona
            //pstmt.setString(3, donadorDto.getDireccion()); //calle
            pstmt.setString(4, "La Paz"); //ciudad
            pstmt.setString(5, "La Paz"); //departamento

            pstmt.setInt(1, emprendedor.getEmprendedorId());
            pstmt.setString(2, emprendedor.getNombre());
            pstmt.setString(3, emprendedor.getApellidos());
            pstmt.setString(4, emprendedor.getFecha_nacimiento());
            pstmt.setInt(5, emprendedor.getNomeroTelefono());
            pstmt.setString(6, emprendedor.getDireccion());
            pstmt.setString(7, emprendedor.getCorreoElectronico());
            pstmt.setInt(8, emprendedor.getTipoemprendimientoId());
            pstmt.setString(9, emprendedor.getContrasenia());


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return emprendedor;
    }

    public EmprendedorDto findEmprendedorByNombre(String nombreEmprendedor) {
        EmprendedorDto result = new EmprendedorDto();
        ProyectoDto result1 = new ProyectoDto();
        PersonaDto result2 = new PersonaDto();
        try(    Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("SELECT nombre_persona, nombre_proyecto " +
                        "FROM emprendedor em " +
                        "JOIN proyecto pr ON  em.id_emprendedor= pr.id_emprendedor " +
                        "JOIN usuario us ON us.id_usuario = em.id_usuario " +
                        "JOIN persona pe ON pe.id_persona = us.id_persona_fk " +
                        "WHERE id_persona = ? GROUP BY pe.nombre_persona , pr.nombre_proyecto; ")
                )
        {
            pstmt.setString(1, nombreEmprendedor);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setEmprendedorId(rs.getInt("id_emprendedor"));
                result1.setNombreProyecto(rs.getString("nombre_proyecto"));
                result2.nombre = rs.getString("nombre_persona");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public List<EmprendedorDto> findAllEmprendedores() {
        List<EmprendedorDto> result = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * " +
                            "FROM donador d " +
                            "JOIN proyecto pr ON d.id_donador=pr.id_proyecto " +
                            "JOIN donacion dn ON d.id_donador=dn.id_donacion " +
                            "JOIN usuario us ON us.id_usuario=d.id_usuario " +
                            "JOIN persona pe ON pe.id_persona=us.id_persona_fk " +
                            "GROUP BY pe.nombre_persona , pr.nombre_proyecto;" +
                            "");
            while (rs.next()) {
                EmprendedorDto emprendedor = new EmprendedorDto();
                emprendedor.setEmprendedorId(rs.getInt("id_emprendedor"));
                emprendedor.setContratoId(rs.getInt("id_contrato"));
                emprendedor.setUsuarioId(rs.getInt("id_usuario"));
                result.add(emprendedor);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
