package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.*;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioBl {
    @Autowired
    PersonaDao personaDao;
    @Autowired
    DonadorDao donadorDao;
    @Autowired
    UsuarioDao usuarioDao;
//Crea una persona
    public PersonaDto crearPersona(PersonaDto persona) {
        return personaDao.crearPersona(persona);
    }
//Busqueda de una persona por Id
    public PersonaDto findPersonaById(Integer personaId) {
        return personaDao.findPersonaById(personaId);
    }
//Listado de todas las personas
    public List<PersonaDto> findAllPersonas() {
        return personaDao.findAllPersonas();
    }
//Crea un usuario
    public UsuarioDto crearUsuario(UsuarioDto usuario) {
        return usuarioDao.crearUsuario(usuario);
    }
//Busca un usuario por id
    public UsuarioDto findUsuarioById(Integer usuarioId) {
        return usuarioDao.findUsuarioById(usuarioId);
    }



//Listado de todos los donadores


}
