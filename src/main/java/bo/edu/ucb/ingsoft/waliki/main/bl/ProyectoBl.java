package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.ProyectoDao;
import bo.edu.ucb.ingsoft.waliki.main.dto.DonacionDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProyectoBl {


    @Autowired
    ProyectoDao proyectoDao;

    public List<ProyectoDto> findAllProyectos() {
        return proyectoDao.findAllProyectos();
    }

    public ProyectoDto findProyectoByVigencia (Integer donacionId) {
        return proyectoDao.findProyectoByVigencia(donacionId);
    }

}
