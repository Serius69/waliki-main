package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.ProyectoBl;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class ProyectoApi {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private ProyectoBl proyectoBl;

    @GetMapping(path = "/proyecto")
    public List<ProyectoDto> findAllProyectos() {
        return proyectoBl.findAllProyectos();
    }

    @GetMapping(path = "/proyecto/{estadoId}")
    public ProyectoDto findProyectoenprogreso(@PathVariable Integer estadoId) {
        ProyectoDto proyecto = proyectoBl.findProyectoEnProgreso(estadoId);
        if (proyecto != null) {
            return proyectoBl.findProyectoEnProgreso(estadoId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existen proyectos en progreso:" + estadoId );
        }
    }
    @GetMapping(path = "/estado/{estadoId}")
    public List<ProyectoDto> findProyectoVigente(@PathVariable Integer estadoId) {
        return proyectoBl.findProyectoVigente(estadoId);
    }
    @GetMapping(path = "/proyecto/{nombreproyecto}")
    public ProyectoDto findProyectoByName(@PathVariable String nombreproyecto) {
        ProyectoDto proyecto = proyectoBl.findProyectoByName(nombreproyecto);
        if (proyecto != null) {
            return proyectoBl.findProyectoByName(nombreproyecto);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe proyectos vigentes" + nombreproyecto );
        }
    }

}
