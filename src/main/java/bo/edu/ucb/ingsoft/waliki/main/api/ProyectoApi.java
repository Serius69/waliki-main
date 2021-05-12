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
    private ProyectoBl proyectoBl;

    @GetMapping(path = "/proyecto")
    public List<ProyectoDto> findAllProyectos() {
        return proyectoBl.findAllProyectos();
    }

    @GetMapping(path = "/proyecto/estado")
    public ProyectoDto findDonadorById(@PathVariable Integer estadoId) {
        ProyectoDto proyecto = proyectoBl.findProyectoVigente(estadoId);
        if (proyecto != null) {
            return proyectoBl.findProyectoVigente(estadoId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la persona con codigo:" + estadoId );
        }
    }

}
