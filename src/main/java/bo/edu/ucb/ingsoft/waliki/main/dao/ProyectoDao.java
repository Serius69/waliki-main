package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProyectoDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SequenceDao sequenceDao;

    public List<ProyectoVigenteDto> findProyectoVigente(Integer estadoId) {
        List<ProyectoVigenteDto> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(
                    "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                            "FROM proyecto p " +
                            "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                            "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                            "JOIN estado e ON p.id_estado = e.id_estado " +
                            "  WHERE p.id_estado = " + estadoId +" " +
                            "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio;" +
                            "     ");

            while (rs.next()) {
                ProyectoVigenteDto proyecto = new ProyectoVigenteDto();
                proyecto.setNombreProyecto(rs.getString("nombre"));
                proyecto.setMontoRQ(rs.getDouble("monto_recaudar"));
                proyecto.setTiempoRestante(rs.getString("fecha_inicio"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<ProyectoEnProcesoDto> findProyectoEnProceso(Integer estadoId) {
        List<ProyectoEnProcesoDto> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(
                    "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                            "FROM proyecto p " +
                            "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                            "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                            "JOIN estado e ON p.id_estado = e.id_estado " +
                            "  WHERE p.id_estado = " + estadoId +" " +
                            "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio;" +
                            "     ");

            while (rs.next()) {
                ProyectoVigenteDto proyecto = new ProyectoVigenteDto();
                proyecto.setNombreProyecto(rs.getString("nombre"));
                proyecto.setMontoRQ(rs.getDouble("monto_recaudar"));
                proyecto.setTiempoRestante(rs.getString("fecha_inicio"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public List<ProyectoDto> findProyectoVigente(Integer estadoId) {
        List<ProyectoDto> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(
                    "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                            "FROM proyecto p " +
                            "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                            "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                            "JOIN estado e ON p.id_estado = e.id_estado " +
                            "  WHERE p.id_estado = " + estadoId +" " +
                            "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio;" +
                            "     ");

            while (rs.next()) {
                ProyectoVigenteDto proyecto = new ProyectoVigenteDto();
                proyecto.setNombreProyecto(rs.getString("nombre"));
                proyecto.setMontoRQ(rs.getDouble("monto_recaudar"));
                proyecto.setTiempoRestante(rs.getString("fecha_inicio"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public List<ProyectoFinalizadoDto> findProyectoVigente(Integer estadoId) {
        List<ProyectoVigenteDto> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(
                    "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                            "FROM proyecto p " +
                            "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                            "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                            "JOIN estado e ON p.id_estado = e.id_estado " +
                            "  WHERE p.id_estado = " + estadoId +" " +
                            "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio;" +
                            "     ");

            while (rs.next()) {
                ProyectoVigenteDto proyecto = new ProyectoVigenteDto();
                proyecto.setNombreProyecto(rs.getString("nombre"));
                proyecto.setMontoRQ(rs.getDouble("monto_recaudar"));
                proyecto.setTiempoRestante(rs.getString("fecha_inicio"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ProyectoDto findProyectoByName(String nombreProyecto) {
        ProyectoDto result = new ProyectoDto();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(
                    "SELECT nombre_proyecto, monto_recaudar, fecha_inicio," +
                            "FROM proyecto p " +
                            "JOIN emprendedor em ON p.id_emprendedor = em.id_emprendedor " +
                            "JOIN usuario us ON us.id_usuario = em.id_usuario " +
                            "JOIN persona pe ON pe.id_persona = us.id_persona_fk" +

                            "  WHERE nombre_proyecto = " + nombreProyecto +" " +
                            "GROUP BY p.nombre_proyecto,pe.nombre_persona, p.monto_recaudar, fecha_inicio, fecha_fin;" +
                            "     ");
            if (rs.next()) {
                result.proyectoId = rs.getInt("id_proyecto");
                result.nombreProyecto = rs.getString("nombre_proyecto");
                result.proyectoId = rs.getInt("id_proyecto");
                result.nombreProyecto = rs.getString("nombre_proyecto");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
