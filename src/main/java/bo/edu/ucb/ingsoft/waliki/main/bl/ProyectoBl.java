package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.*;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoBl {
    @Autowired
    ProyectoDao proyectoDao;
    //Crear un nuevo proyecto
    public ProyectoDto addProyecto(ProyectoDto proyecto) {
        return proyectoDao.crearProyecto(proyecto);
    }

    //Listado proyectos vigentes
    public List<ProyectoVigenteDto> findProyectoVigente (Integer estado) {
        return proyectoDao.findProyectoVigente(estado);
    }
    //Listado proyectos en proceso
    public List<ProyectoEnProcesoDto> findProyectoEnProceso (Integer estadoId) {
        return proyectoDao.findProyectoEnProceso(estadoId);
    }
    //Listado proyectos finalizados
    public List<ProyectoFinalizadoDto> findProyectosFinalizado (Integer estadoId) {
        return proyectoDao.findProyectoFinalizado(estadoId);
    }
    //Listado proyectos todos
    public List<ConsultaProyectoDto> findAllProyectos () {
        return proyectoDao.findAllProyectos();
    }
    //Listado proyectos vigentes

    public ProyectoDto findProyectoByName (String nombreProyecto) {
        return proyectoDao.findProyectoByName(nombreProyecto);
    }

}
