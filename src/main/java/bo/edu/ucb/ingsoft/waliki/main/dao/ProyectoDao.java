package bo.edu.ucb.ingsoft.waliki.main.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoEnProcesoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoFinalizadoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoVigenteDto;
import java.util.List;
import java.util.Date;

@Service
public class ProyectoDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;

    //Crear un nuevo proyecto
    public ProyectoDto crearProyecto (ProyectoDto proyecto) {
        proyecto.setProyectoId(sequenceDao.getPrimaryKeyForTable("proyecto"));


        Date fechaActual = new Date();
        DateTimeFormatter horaActual = DateTimeFormatter.ofPattern("HH:mm:ss");

        try(    Connection conn = dataSource.getConnection();
                PreparedStatement stmts = conn.prepareStatement("INSERT INTO proyecto VALUES (?,?,?,?,?,?,?,?,?,?); INSERT INTO recompensas VALUES (?,?,?,?,?)")

        ) {
            //--Tabla proyecto
            stmts.setInt(1, proyecto.getProyectoId());//id_proyecto
            stmts.setString(2, proyecto.getNombreProyecto()); //
            stmts.setString(3, proyecto.getDescripcion());
            stmts.setDouble(4, proyecto.getMontoRecaudar());
            stmts.setInt(5,1); // id_emprendedor
            stmts.setString(6, String.valueOf(horaActual)); //hora_inicio
            stmts.setString(7, String.valueOf(horaActual)); //hora-fin
            stmts.setInt(8, 1); //id_estado
            stmts.setString(9, String.valueOf(fechaActual)); //fecha_inicio
            stmts.setString(10, "mm/dd/yyyy"); //fecha_final
            //--Tabla recompensa
            stmts.setInt(11, 1); // id_recompensa
            stmts.setInt(12, 10); // valor_min
            stmts.setInt(13, 200); // valor_max
            stmts.setString(14, "Un prototipo"); // recompensa
            stmts.setInt(15, proyecto.getProyectoId()); // id_proyecto
            stmts.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return proyecto;
    }

    //Listado de proyectos vigentes
    public List<ProyectoVigenteDto> findProyectoVigente(Integer estadoId) {
        List<ProyectoVigenteDto> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                     "FROM proyecto p JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                     "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                     "JOIN estado e ON p.id_estado = e.id_estado " +
                     "WHERE p.id_estado = ? " +
                     "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio; ")

        ){  pstmt.setInt(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

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
    //Listado de proyectos en proceso
    public List<ProyectoEnProcesoDto> findProyectoEnProceso(Integer estadoId) {
        List<ProyectoEnProcesoDto> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                     "FROM proyecto p JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                     "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                     "JOIN estado e ON p.id_estado = e.id_estado " +
                     "WHERE p.id_estado = ? " +
                     "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio; ")
        ){ pstmt.setInt(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProyectoEnProcesoDto proyecto = new ProyectoEnProcesoDto();
                proyecto.setNombreProyecto(rs.getString("nombre"));
                proyecto.setFechaInicio(rs.getString("monto_recaudar"));
                proyecto.setFechaFinal(rs.getString("fecha_inicio"));
                proyecto.setProceso(rs.getDouble("fecha_inicio"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    //Listado de todos los proyectos
    public List<ProyectoDto> findAllProyectos(Integer estadoId) {
        List<ProyectoDto> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                     "FROM proyecto p JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                     "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                     "JOIN estado e ON p.id_estado = e.id_estado " +
                     "WHERE p.id_estado = ? " +
                     "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio; ")
        ){
            pstmt.setInt(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProyectoDto proyecto = new ProyectoDto();
                proyecto.setNombreProyecto(rs.getString("nombre"));
                proyecto.setMontoRecaudar(rs.getDouble("monto_recaudar"));
                proyecto.setFechaInicio(rs.getString("fecha_inicio"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    //Listado de proyectos finalizados
    public List<ProyectoFinalizadoDto> findProyectosFinalizados(Integer estadoId) {
        List<ProyectoFinalizadoDto> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                     "FROM proyecto p JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                     "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                     "JOIN estado e ON p.id_estado = e.id_estado " +
                     "WHERE p.id_estado = ? " +
                     "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio; ")

        ){  pstmt.setInt(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProyectoFinalizadoDto proyecto = new ProyectoFinalizadoDto();
                proyecto.setNombreProyecto(rs.getString("nombre"));
                proyecto.setMontoFinalRecaudado(rs.getDouble("monto_recaudar"));
                proyecto.setFechaInicio(rs.getString("fecha_inicio"));
                proyecto.setFechaFin(rs.getString("fech_fin"));
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
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT nombre_proyecto, monto_recaudar, fecha_inicio " +
                     "FROM proyecto p " +
                     "JOIN emprendedor em ON p.id_emprendedor = em.id_emprendedor " +
                     "JOIN usuario us ON us.id_usuario = em.id_usuario " +
                     "JOIN persona pe ON pe.id_persona = us.id_persona_fk " +
                     "WHERE nombre_proyecto = ? " +
                     "GROUP BY p.nombre_proyecto,pe.nombre_persona, p.monto_recaudar, fecha_inicio, fecha_fin; ")

        ){ pstmt.setString(1, nombreProyecto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result.setProyectoId(rs.getInt("id_proyecto"));
                result.setNombreProyecto(rs.getString("nombre_proyecto"));
                result.setEstadoId(rs.getInt("estado"));
                result.setEmprendedorId(rs.getInt("id_persona"));
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
