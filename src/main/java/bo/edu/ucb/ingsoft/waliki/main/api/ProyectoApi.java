package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.ProyectoBl;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;

public class ProyectoApi {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private ProyectoBl proyectoBl;

    @GetMapping(path = "/proyecto")
    public List<ProyectoDto> findAllProyectos() {
        return proyectoBl.findAllProyectos();
    }

    @GetMapping(path = "/proyecto/estado/1")
    public ProyectoDto findProyectoenprogreso(@PathVariable Integer estadoId) {
        ProyectoDto proyecto = proyectoBl.findProyectoEnProgreso(estadoId);
        if (proyecto != null) {
            return proyectoBl.findProyectoEnProgreso(estadoId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existen proyectos en progreso:" + estadoId );
        }
    }
    @GetMapping(path = "/proyecto/estado/2")
    public ProyectoDto findProyectoVigente(@PathVariable Integer estadoId) {
        ProyectoDto proyecto = proyectoBl.findProyectoVigente(estadoId);
        if (proyecto != null) {
            return proyectoBl.findProyectoVigente(estadoId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe proyectos vigentes" + estadoId );
        }
    }
    @GetMapping(path = "/proyecto/{nombreproyecto}")
    public ProyectoDto findProyectoByName(@PathVariable String nombreProyecto) {
        ProyectoDto proyecto = proyectoBl.findProyectoByName(nombreProyecto);
        if (proyecto != null) {
            return proyectoBl.findProyectoByName(nombreProyecto);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe proyectos vigentes" + nombreProyecto );
        }
    }

}
