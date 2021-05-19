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

    //Listado proyectos vigentes
    public List<ProyectoVigenteDto> findProyectoVigente (Integer estadoId) {
        return proyectoDao.findProyectoVigente(estadoId);
    }
    //Listado proyectos en proceso
    public List<ProyectoEnProcesoDto> findProyectoEnProceso (Integer estadoId) {
        return proyectoDao.findProyectoEnProceso(estadoId);
    }
    //Listado proyectos finalizados
    public List<ProyectoFinalizadoDto> findProyectosFinalizados (Integer estadoId) {
        return proyectoDao.findProyectosFinalizados(estadoId);
    }
    //Listado proyectos todos
    public List<ProyectoDto> findAllProyectos (Integer estadoId) {
        return proyectoDao.findAllProyectos(estadoId);
    }
    //Listado proyectos vigentes
    public ProyectoDto findProyectoByName (String nombreProyecto) {
        return proyectoDao.findProyectoByName(nombreProyecto);
    }

}
