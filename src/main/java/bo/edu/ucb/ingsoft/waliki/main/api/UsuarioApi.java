package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.DonadorBl;
import bo.edu.ucb.ingsoft.waliki.main.bl.UsuarioBl;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;

public class UsuarioApi {

    @Autowired
    public DataSource dataSource;
    @Autowired
    private UsuarioBl usuarioBl;
    @Autowired
    private DonadorBl donadorBl;
    @Autowired
    private UsuarioBl gestionPersonaBl;

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

    @GetMapping(path = "/donador/{donadorId}")
    public DonadorDto findDonadorById(@PathVariable Integer personaId) {
        DonadorDto donador = usuarioBl.findDonadorById(personaId);
        if (donador != null) {
            return usuarioBl.findDonadorById(personaId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la persona con codigo:" + personaId );
        }
    }

    @GetMapping(path = "/donador")
    public List<DonadorDto> findAllDonadores() {
        return usuarioBl.findAllDonadores();
    }

//crear un nuevo donador
    @PostMapping(path = "/donador")
    public DonadorDto createDonador(@RequestBody DonadorDto donador, UsuarioDto usuario, PersonaDto persona, DireccionDto direccion) {
        // Validar que los datos enviados son correctos.
        if (usuario.usuario == null || usuario.usuario.trim().equals("")) {  // nombre: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario debe ser obligatorio" );
        }
        if (persona.correo_electronico == null || persona.correo_electronico.equals("")) {  // nombre: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electronico debe ser obligatorio" );
        }
        if (persona.telefono == null || persona.telefono.trim().equals("")) {  // nombre: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El numero de telefono debe ser obligatorio" );
        }
        if (direccion.zona == null || direccion.zona.trim().equals("")) {  // nombre: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la direccion debe ser obligatoria" );
        }
        if (usuario.contrasena == null || usuario.contrasena.trim().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contrasenia debe ser obligatoria" );
        }

        return usuarioBl.crearDonador(donador);
    }

    @GetMapping(path = "/contrato/contratoId")
    public ContratoDto findContrato (@PathVariable Integer contratoId) {
        ContratoDto contrato = donadorBl.findContrato(contratoId);
        if (contrato != null) {
            return donadorBl.findContrato(contratoId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la persona con codigo:" + contratoId );
        }
    }

}
