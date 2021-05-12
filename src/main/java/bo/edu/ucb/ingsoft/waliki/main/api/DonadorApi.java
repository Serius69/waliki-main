package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.DonadorBl;
import bo.edu.ucb.ingsoft.waliki.main.dto.DonadorDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;

public class DonadorApi {

    @Autowired
    public DataSource dataSource;

    @Autowired
    private DonadorBl donadorBl;

    @GetMapping(path = "/donador/{donadorId}")
    public DonadorDto findPersonaById(@PathVariable Integer personaId) {
        DonadorDto donador = donadorBl.findDonadorById(personaId);
        if (donador != null) {
            return donadorBl.findDonadorById(personaId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la persona con codigo:" + personaId );
        }
    }

    @GetMapping(path = "/donador")
    public List<DonadorDto> findAllDonadores() {
        return donadorBl.findAllDonadores();
    }


    @PostMapping(path = "/donador")
    public DonadorDto createDonador(@RequestBody DonadorDto donador) {
        // Validar que los datos enviados son correctos.
        if (donador.donadorId == null || donador.contratoId.equals("")) {  // nombre: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre debe ser obligatorio" );
        }

        if (donador.donadorId == null || donador.contratoId.equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El apellido debe ser obligatorio" );
        }

        return donadorBl.crearDonador(donador);
    }

}
