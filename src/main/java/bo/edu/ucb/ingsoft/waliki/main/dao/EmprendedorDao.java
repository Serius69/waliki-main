package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.DonadorDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.EmprendedorDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmprendedorDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SequenceDao sequenceDao;

    public EmprendedorDto crearEmprendedor (EmprendedorDto emprendedor) {
        emprendedor.emprendedorId = sequenceDao.getPrimaryKeyForTable("emprendedor");
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("" +
                    "INSERT INTO donador VALUES ("
                    + emprendedor.emprendedorId +", '"
                    + emprendedor.contratoId +"', '"
                    + emprendedor.usuarioId+"') ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return emprendedor;
    }

    public EmprendedorDto findEmprendedorByNombre(String nombreEmprendedor) {
        EmprendedorDto result = new EmprendedorDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
    //areglar sql
            ResultSet rs = stmt.executeQuery(
                    "SELECT pr.nombre, monto, SUM(monto)" +
                            "FROM donador d " +
                            "JOIN proyecto pr ON  d.id_donador= pr.id_proyecto " +
                            "JOIN donacion dn ON d.id_donador = dn.id_donacion " +
                            "JOIN usuario us ON us.id_usuario = d.id_usuario " +
                            "JOIN persona pe ON pe.id_persona = us.id_persona" +

                            "  WHERE id_persona = " + nombreEmprendedor +" " +
                            "GROUP BY pe.nombre , pr.nombre, dn.monto;" +
                            "     ");

            if (rs.next()) {
                result.emprendedorId = rs.getInt("id_emprendedor");
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

    public EmprendedorDto findEmprendedorById(Integer emprendedorId) {
        EmprendedorDto result = new EmprendedorDto();
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

                            "  WHERE id_persona = " + emprendedorId +" " +
                            "GROUP BY pe.nombre , pr.nombre, dn.monto;" +
                            "     ");

            if (rs.next()) {
                result.emprendedorId = rs.getInt("id_emprendedor");
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

    public List<EmprendedorDto> findAllEmprendedores() {
        List<EmprendedorDto> result = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT pe.nombre, pr.nombre, monto " +
                            "FROM donador d " +
                            "JOIN proyecto pr ON d.id_donador=pr.id_proyecto " +
                            "JOIN donacion dn ON d.id_donador=dn.id_donacion " +
                            "JOIN usuario us ON us.id_usuario=d.id_usuario " +
                            "JOIN persona pe ON pe.id_persona=us.id_persona " +
                            "GROUP BY pe.nombre , pr.nombre, dn.monto;" +
                            "");
            while (rs.next()) {
                EmprendedorDto emprendedor = new EmprendedorDto();
                emprendedor.emprendedorId = rs.getInt("id_emprendedor");
                emprendedor.contratoId = rs.getInt("id_contrato");
                emprendedor.usuarioId = rs.getInt("id_usuario");
                result.add(emprendedor);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
