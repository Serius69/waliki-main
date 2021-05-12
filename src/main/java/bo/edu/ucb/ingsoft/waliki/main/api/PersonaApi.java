package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.PersonaBl;
import bo.edu.ucb.ingsoft.waliki.main.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class PersonaApi {

    @Autowired
    public DataSource dataSource;

    @Autowired
    private PersonaBl gestionPersonaBl;

    @GetMapping(path = "/persona/{personaId}")
    public PersonaDto findPersonaById(@PathVariable Integer personaId) {
        PersonaDto persona = gestionPersonaBl.findPersonaById(personaId);
        if (persona != null) {
            return gestionPersonaBl.findPersonaById(personaId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la persona con codigo:" + personaId );
        }
    }

    @GetMapping(path = "/persona")
    public List<PersonaDto> findAllPersonas() {
        return gestionPersonaBl.findAllPersonas();
    }

    @PostMapping(path = "/persona")
    public PersonaDto createPersona(@RequestBody PersonaDto persona) {
        // Validar que los datos enviados son correctos.
        if (persona.nombre == null || persona.nombre.trim().equals("")) {  // nombre: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre debe ser obligatorio" );
        }

        if (persona.apellidoPaterno == null || persona.apellidoPaterno.trim().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El apellido debe ser obligatorio" );
        }

        return gestionPersonaBl.crearPersona(persona);
    }

}
