package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.ContratoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ImagenDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.PersonaDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoDto;
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
                    "SELECT id_proyecto, nombre, monto_recaudar, fecha_inicio, fecha_fin " +
                    "FROM proyecto ");
            while (rs.next()) {
                ProyectoDto proyecto = new ProyectoDto();
                proyecto.proyectoId = rs.getInt("id_persona");
                proyecto.nombre = rs.getString("nombre");
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
                    "SELECT nombre, monto_recaudar, fecha_inicio " +
                            "FROM proyecto p " +
                            "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                            "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                            "JOIN estado e ON p.id_estado = e.id_estado " +
                            "  WHERE p.id_estado = " + 1 +" " +
                            "GROUP BY p.nombre, p.monto_recaudar, p.fecha_inicio;" +
                            "     ");

            if (rs.next()) {
                result.nombre = rs.getString("nombre");
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
                    "SELECT src_imagen, p.nombre, fecha_inicio, fecha_fin " +
                            "FROM proyecto p " +
                            "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                            "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                            "JOIN estado e ON p.id_estado = e.id_estado " +
                            "  WHERE p.id_estado = " + 2 +" " +
                            "GROUP BY img.src_imagen, p.nombre, p.fecha_inicio, p.fecha_fin;" +
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

}
