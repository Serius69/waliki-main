package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.ProyectoDao;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProyectoBl {
    @Autowired
    ProyectoDao proyectoDao;

    public List<ProyectoDto> findAllProyectos() {
        return proyectoDao.findAllProyectos();
    }

    public ProyectoDto findProyectoVigente (Integer estadoId) {
        return proyectoDao.findProyectoVigente(estadoId);
    }
    public ProyectoDto findProyectoEnProgreso (Integer estadoId) {
        return proyectoDao.findProyectoEnProgreso(estadoId);
    }

}
