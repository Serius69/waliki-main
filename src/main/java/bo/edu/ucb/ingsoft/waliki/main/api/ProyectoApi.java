package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.ProyectoBl;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoEnProcesoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoFinalizadoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.ProyectoVigenteDto;
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

    //Listado proyecto vigente
    @GetMapping(path = "/estado/{estadoId}")
    public List<ProyectoVigenteDto> findProyectoVigente(@PathVariable Integer estadoId) {
        return proyectoBl.findProyectoVigente(estadoId);
    }
    //Listado proyecto en proceso
    @GetMapping(path = "/estado/{estadoId}")
    public List<ProyectoEnProcesoDto> findProyectoEnProceso(@PathVariable Integer estadoId) {
        return proyectoBl.findProyectoEnProceso(estadoId);
    }
    //Listado proyecto finalizado
    @GetMapping(path = "/estado/{estadoId}")
    public List<ProyectoFinalizadoDto> findProyectoFinalizado(@PathVariable Integer estadoId) {
        return proyectoBl.findProyectosFinalizados(estadoId);
    }
    //Listado proyecto todos
    @GetMapping(path = "/estado/{estadoId}")
    public List<ProyectoDto> findAllProyectos(@PathVariable Integer estadoId) {
        return proyectoBl.findAllProyectos(estadoId);
    }

    //Buscar proyecto por nombre
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
