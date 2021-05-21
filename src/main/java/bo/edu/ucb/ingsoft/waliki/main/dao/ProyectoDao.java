package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProyectoDao {
    @Autowired
    private DataSource dataSource2;
    @Autowired
    private SequenceDao sequenceDao;

    public List<PrincipalProyectosDto> paginaPrincipal(){
        List<PrincipalProyectosDto> result = new ArrayList<>();
        try ( Connection conn2 = dataSource2.getConnection();
             //Statement stmt = conn.createStatement()
              PreparedStatement pstmt = conn2.prepareStatement("" +
                     "SELECT p.id_proyecto, nombre_proyecto, descripcion, monto_recaudar, estado " +
                     "FROM proyecto p " +
                     "JOIN estado e on p.id_estado = e.id_estado " +
                     "ORDER BY p.id_proyecto ; ")

        ){  //pstmt.setInt(1, estadoId);
            /*ResultSet rs = stmt.executeQuery("SELECT nombre_proyecto, monto_recaudar, fecha_inicio FROM proyecto p " +
                    "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                    "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                    "JOIN estado e ON p.id_estado = e.id_estado " +
                    "WHERE p.id_estado = 1 " +
                    "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio; ");*/
            //stmt.setInt(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PrincipalProyectosDto proyecto = new PrincipalProyectosDto();
                proyecto.setProyectoId(rs.getInt("id_proyecto"));
                proyecto.setProyectoNombre(rs.getString("nombre_proyecto"));
                proyecto.setMonto_recaudar(rs.getDouble("monto_recaudar"));
                proyecto.setDescripcion(rs.getString("descripcion"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //Crear un nuevo proyecto
    public ProyectoDto crearProyecto (ProyectoDto proyectoDto) {
        proyectoDto.setProyectoId(sequenceDao.getPrimaryKeyForTable("proyecto"));
        proyectoDto.setEmprendedorId(23);
        proyectoDto.setEstadoId(1);
        proyectoDto.setHoraInicio("20:00:00");
        proyectoDto.setHoraFin("20:00:00");
        proyectoDto.setFechaInicio("5/20/2021");
        proyectoDto.setFechaFin("6/20/2021");
        proyectoDto.setMontoRecaudar(proyectoDto.getMontoRecaudar());
        try (Connection conn = dataSource2.getConnection()) //cerrado de conexion
        {
            PreparedStatement stmts = conn.prepareStatement("" +
                    "INSERT INTO proyecto (id_proyecto, nombre_proyecto, descripcion, monto_recaudar, id_emprendedor, hora_inicio, hora_fin, id_estado, fecha_inicio, fecha_fin) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?); ");
            stmts.setInt(1, proyectoDto.getProyectoId());//id_proyecto
            stmts.setString(2, proyectoDto.getNombreProyecto()); //nombre proyecto
            stmts.setString(3, proyectoDto.getDescripcion());// descripcion
            stmts.setDouble(4, proyectoDto.getMontoRecaudar()); // monto_recaudar
            stmts.setInt(5, proyectoDto.getEmprendedorId()); // id_emprendedor
            stmts.setString(6, proyectoDto.getHoraInicio()); //hora_inicio
            stmts.setString(7, proyectoDto.getHoraFin()); //hora-fin
            stmts.setInt(8, proyectoDto.getEstadoId()); //id_estado
            stmts.setString(9, proyectoDto.getFechaInicio()); //fecha_inicio
            stmts.setString(10, proyectoDto.getFechaFin()); //fecha_final
            stmts.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // No hacer nada intencionalemte;
            /*
        PreparedStatement stmts = conn.prepareStatement("" +
                    "INSERT INTO proyecto (id_proyecto, nombre_proyecto, descripcion, monto_recaudar, id_emprendedor, hora_inicio, hora_fin, id_estado, fecha_inicio, fecha_fin) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?); ");
            stmts.setInt(1, proyecto.getProyectoId());//id_proyecto
            stmts.setString(2, proyecto.getNombreProyecto()); //nombre proyecto
            stmts.setString(3, proyecto.getDescripcion());// descripcion
            stmts.setDouble(4, proyecto.getMontoRecaudar()); // monto_recaudar
            stmts.setInt(5,proyecto.getEmprendedorId()); // id_emprendedor
            stmts.setString(6, proyecto.getHoraInicio()); //hora_inicio
            stmts.setString(7, proyecto.getHoraFin()); //hora-fin
            stmts.setInt(8, proyecto.getEstadoId()); //id_estado
            stmts.setString(9, proyecto.getFechaInicio()); //fecha_inicio
            stmts.setString(10, proyecto.getFechaFin()); //fecha_final
         */
        return proyectoDto;
/*
        Date fechaActual = new Date();
        DateTimeFormatter horaActual = DateTimeFormatter.ofPattern("HH:mm:ss");
        try(    Connection conn = dataSource.getConnection();
                PreparedStatement stmts = conn.prepareStatement("" +
                        "INSERT INTO proyecto VALUES (?,?,?,?,?,?,?,?,?,?); " +
                        "INSERT INTO recompensas VALUES (?,?,?,?,?);")

        ) {
            //--Tabla proyecto
            stmts.setInt(1, 101);//id_proyecto
            stmts.setString(2, proyecto.getNombreProyecto()); //
            stmts.setString(3, proyecto.getDescripcion());
            stmts.setDouble(4, proyecto.getMontoRecaudar());
            stmts.setInt(5,1); // id_emprendedor
            stmts.setString(6, "20:00"); //hora_inicio
            stmts.setString(7, "20:00"); //hora-fin
            stmts.setInt(8, 1); //id_estado
            stmts.setString(9, "5/20/2021"); //fecha_inicio
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
        return proyecto;*/
    }

    //Listado de proyectos vigentes
    public List<ProyectoVigenteDto> findProyectoVigente(Integer estadoId) {
        List<ProyectoVigenteDto> result = new ArrayList<>();
        try (Connection conn = dataSource2.getConnection();
             //Statement stmt = conn.createStatement()
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT nombre_proyecto, monto_recaudar, split_part(fecha_fin, '/', 2) AS diasfaltantes " +
                     "FROM proyecto p " +
                     "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                     "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                     "JOIN estado e ON p.id_estado = e.id_estado " +
                     "WHERE p.id_estado = ? " +
                     "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio,p.fecha_fin " +
                     "ORDER BY p.fecha_inicio DESC; ")

        ){  pstmt.setInt(1, estadoId);
            /*ResultSet rs = stmt.executeQuery("SELECT nombre_proyecto, monto_recaudar, fecha_inicio FROM proyecto p " +
                    "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                    "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                    "JOIN estado e ON p.id_estado = e.id_estado " +
                    "WHERE p.id_estado = 1 " +
                    "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio; ");*/
            //stmt.setInt(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProyectoVigenteDto proyecto = new ProyectoVigenteDto();
                //proyecto.setProyectoId(rs.getInt("id_proyecto"));
                proyecto.setNombreProyecto(rs.getString("nombre_proyecto"));
                proyecto.setMontoRQ(rs.getDouble("monto_recaudar"));
                proyecto.setTiempoRestante(rs.getString("diasfaltantes"));
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
        try (Connection conn = dataSource2.getConnection();
             //Statement stmt = conn.createStatement()
             PreparedStatement pstmt = conn.prepareStatement("" +
                     //proyectos q se encuentran con donaciones
                     "SELECT p.id_proyecto, nombre_proyecto, monto_recaudar, fecha_inicio, fecha_fin, SUM(monto) " +
                     "FROM proyecto p " +
                     //"JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                     //"JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                     "JOIN estado e ON p.id_estado = e.id_estado " +
                     "JOIN donacion d on p.id_proyecto = d.id_proyecto " +
                     "WHERE p.id_estado = ? " +
                     "GROUP BY p.id_proyecto,p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio,p.fecha_fin; ")
                     //"ORDER BY p.id_proyecto; ")

        ){  pstmt.setInt(1, estadoId);
            /*ResultSet rs = stmt.executeQuery("SELECT nombre_proyecto, monto_recaudar, fecha_inicio FROM proyecto p " +
                    "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                    "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                    "JOIN estado e ON p.id_estado = e.id_estado " +
                    "WHERE p.id_estado = 1 " +
                    "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio; ");*/
            //stmt.setInt(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProyectoEnProcesoDto proyecto = new ProyectoEnProcesoDto();
                proyecto.setProyectoId(rs.getInt("id_proyecto"));
                proyecto.setNombreProyecto(rs.getString("nombre_proyecto"));
                proyecto.setFechaInicio(rs.getString("fecha_inicio"));
                proyecto.setFechaFinal(rs.getString("fecha_fin"));
                proyecto.setProceso(rs.getDouble("SUM"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    //Listado de proyectos finalizados
    public List<ProyectoFinalizadoDto> findProyectoFinalizado(Integer estadoId) {
        List<ProyectoFinalizadoDto> result = new ArrayList<>();
        try (Connection conn = dataSource2.getConnection();
             //Statement stmt = conn.createStatement()
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT p.id_proyecto, nombre_proyecto, SUM(dn.monto), fecha_inicio , fecha_fin " +
                     "FROM proyecto p " +
                     //"JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                     //"JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                     "JOIN estado e ON p.id_estado = e.id_estado " +
                     "JOIN donacion dn ON p.id_proyecto = dn.id_proyecto " +
                     "WHERE p.id_estado = ? " +
                     "GROUP BY p.id_proyecto,p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio,p.fecha_fin " +
                     "ORDER BY p.id_proyecto; ")

        ){  pstmt.setInt(1, estadoId);
            /*ResultSet rs = stmt.executeQuery("SELECT nombre_proyecto, monto_recaudar, fecha_inicio FROM proyecto p " +
                    "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                    "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                    "JOIN estado e ON p.id_estado = e.id_estado " +
                    "WHERE p.id_estado = 1 " +
                    "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio; ");*/
            //stmt.setInt(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProyectoFinalizadoDto proyecto = new ProyectoFinalizadoDto();
                proyecto.setProyectoId(rs.getInt("id_proyecto"));
                proyecto.setNombreProyecto(rs.getString("nombre_proyecto"));
                proyecto.setMontoFinalRecaudado(rs.getDouble("SUM"));
                proyecto.setFechaInicio(rs.getString("fecha_inicio"));
                proyecto.setFechaFin(rs.getString("fecha_fin"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    //Listado de todos los proyectos
    public List<ConsultaProyectoDto> findAllProyectos() {
        List<ConsultaProyectoDto> result = new ArrayList<>();
        try (Connection conn = dataSource2.getConnection();
             //Statement stmt = conn.createStatement()
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT p.id_proyecto, nombre_proyecto, descripcion , monto_recaudar, estado " +
                     "FROM proyecto p " +
                     "JOIN estado e on p.id_estado = e.id_estado " +
                     "ORDER BY p.id_proyecto ; ")

        ){  //pstmt.setInt(1, estadoId);
            /*ResultSet rs = stmt.executeQuery("SELECT nombre_proyecto, monto_recaudar, fecha_inicio FROM proyecto p " +
                    "JOIN imagen_proyecto ip ON p.id_proyecto = ip.id_proyecto " +
                    "JOIN imagen img ON ip.id_imagen = img.id_imagen " +
                    "JOIN estado e ON p.id_estado = e.id_estado " +
                    "WHERE p.id_estado = 1 " +
                    "GROUP BY p.nombre_proyecto, p.monto_recaudar, p.fecha_inicio; ");*/
            //stmt.setInt(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ConsultaProyectoDto proyecto = new ConsultaProyectoDto();
                proyecto.setProyectoId(rs.getInt("id_proyecto"));
                proyecto.setNombreProyecto(rs.getString("nombre_proyecto"));
                proyecto.setMontoRecaudar(rs.getDouble("monto_recaudar"));
                proyecto.setDescripcion(rs.getString("descripcion"));
                proyecto.setEstado(rs.getString("estado"));
                result.add(proyecto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public ConsultaProyectoDto findProyectobyId(Integer proyectoId) {
        ConsultaProyectoDto result = new ConsultaProyectoDto();
        try (Connection conn = dataSource2.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT p.id_proyecto, nombre_proyecto, descripcion, monto_recaudar, estado " +
                     "FROM proyecto p " +
                     "JOIN emprendedor em ON p.id_emprendedor = em.id_emprendedor " +
                     "JOIN usuario us ON us.id_usuario = em.id_usuario " +
                     "JOIN persona pe ON pe.id_persona = us.id_persona_fk " +
                     "JOIN estado e ON p.id_estado = e.id_estado " +
                     "WHERE id_proyecto = ? " +
                     "GROUP BY p.id_proyecto, p.nombre_proyecto, descripcion, p.monto_recaudar, e.estado ; " +
                     "")

        ){  pstmt.setInt(1, proyectoId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result.setProyectoId(rs.getInt("id_proyecto"));
                result.setNombreProyecto(rs.getString("nombre_proyecto"));
                result.setEstado(rs.getString("estado"));
                result.setMontoRecaudar(rs.getDouble("monto_recaudar"));
                result.setDescripcion(rs.getString("descripcion"));
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // Buscar un proyecto por su nombre
    public ProyectoDto findProyectoByName(String nombreProyecto) {
        ProyectoDto result = new ProyectoDto();
        try (Connection conn = dataSource2.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("" +
                     "SELECT p.id_proyecto, nombre_proyecto, monto_recaudar, fecha_inicio, id_estado " +
                     "FROM proyecto p " +
                     "JOIN emprendedor em ON p.id_emprendedor = em.id_emprendedor " +
                     "JOIN usuario us ON us.id_usuario = em.id_usuario " +
                     "JOIN persona pe ON pe.id_persona = us.id_persona_fk " +
                     "WHERE nombre_proyecto = ? " +
                     "GROUP BY p.id_proyecto, p.nombre_proyecto, p.monto_recaudar, fecha_inicio, fecha_fin, p.id_estado ; " +
                     "")

        ){  pstmt.setString(1, nombreProyecto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result.setProyectoId(rs.getInt("id_proyecto"));
                result.setNombreProyecto(rs.getString("nombre_proyecto"));
                result.setEstadoId(rs.getInt("estado"));
                result.setEmprendedorId(rs.getInt("id_persona"));
                result.setEstadoId(rs.getInt("id_estado"));
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
