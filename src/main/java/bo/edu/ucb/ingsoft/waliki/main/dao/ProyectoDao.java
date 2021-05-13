package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.ImagenDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SequenceDao sequenceDao;

    public List<ProyectoDto> findAllProyectos() {
        List<ProyectoDto> result = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("" +
                    "SELECT id_proyecto, nombre_proyecto, monto_recaudar, fecha_inicio, fecha_fin " +
                    "FROM proyecto ");
            while (rs.next()) {
                ProyectoDto proyecto = new ProyectoDto();
                proyecto.proyectoId = rs.getInt("id_persona");
                proyecto.nombreProyecto = rs.getString("nombre");
                proyecto.montoRecaudar = rs.getString("monto_recaudar");
                proyecto.fechaInicio = rs.getString("fecha_inicio");
                proyecto.fechaFin = rs.getString("fecha_fin");
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ProyectoDto findProyectoVigente(Integer proyectoId) {
        ProyectoDto result = new ProyectoDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                            "FROM proyecto p " +
                            "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                            "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                            "JOIN estado e ON p.id_estado = e.id_estado " +
                            "  WHERE p.id_estado = " + 1 +" " +
                            "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio;" +
                            "     ");

            if (rs.next()) {
                result.nombreProyecto = rs.getString("nombre");
                result.montoRecaudar= rs.getString("monto_recaudar");
                result.fechaInicio= rs.getString("fecha_inicio");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ProyectoDto findProyectoEnProgreso (Integer proyectoId) {
        ProyectoDto result = new ProyectoDto();
        ImagenDto result2 = new ImagenDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT src_imagen, p.nombre_proyecto, fecha_inicio, fecha_fin " +
                            "FROM proyecto p " +
                            "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                            "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                            "JOIN estado e ON p.id_estado = e.id_estado " +
                            "  WHERE p.id_estado = " + 2 +" " +
                            "GROUP BY img.src_imagen, p.nombre_proyecto, p.fecha_inicio, p.fecha_fin;" +
                            "     ");

            if (rs.next()) {
                result2.srcImagen = rs.getString("src_imagen");
                result.montoRecaudar= rs.getString("p.nombre");
                result.fechaInicio= rs.getString("fecha_inicio");
                result.fechaFin= rs.getString("fecha_fin");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ProyectoDto findProyectoByName(String nombreProyecto) {
        ProyectoDto result = new ProyectoDto();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

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
