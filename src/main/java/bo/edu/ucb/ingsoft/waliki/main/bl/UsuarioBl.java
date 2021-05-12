package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.DonadorDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.PersonaDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.UsuarioDao;
import bo.edu.ucb.ingsoft.waliki.main.dto.DonadorDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.PersonaDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioBl {

    @Autowired
    PersonaDao personaDao;
    DonadorDao donadorDao;
    @Autowired
    UsuarioDao usuarioDao;

    public PersonaDto crearPersona(PersonaDto persona) {
        // Computamos el numero de seguro social, conformado por los tres primeros caracteres
        // del nombre mas los tres primeros del apelliod
        persona.nroSS = persona.nombre.substring(0,3).toUpperCase() + persona.apellidoPaterno.substring(0,3);

        return personaDao.crearPersona(persona);
    }

    public PersonaDto findPersonaById(Integer personaId) {
        return personaDao.findPersonaById(personaId);
    }

    public List<PersonaDto> findAllPersonas() {
        return personaDao.findAllPersonas();
    }


    public UsuarioDto crearUsuario(UsuarioDto usuario) {
        // Computamos el numero de seguro social, conformado por los tres primeros caracteres
        // del nombre mas los tres primeros del apelliod
        //donador.donadorId = donador.nombre.substring(0,3).toUpperCase() + donador.apellido.substring(0,3);

        return usuarioDao.crearUsuario(usuario);
    }

    public UsuarioDto findUsuarioById(Integer usuarioId) {
        return usuarioDao.findUsuarioById(usuarioId);
    }

    public DonadorDto crearDonador(DonadorDto donador) {
        // Computamos el numero de seguro social, conformado por los tres primeros caracteres
        // del nombre mas los tres primeros del apelliod
        //donador.donadorId = donador.nombre.substring(0,3).toUpperCase() + donador.apellido.substring(0,3);

        return donadorDao.crearDonador(donador);
    }

    public DonadorDto findDonadorById(Integer donadorId) {
        return donadorDao.findDonadorById(donadorId);
    }

    public List<DonadorDto> findAllDonadores() {
        return donadorDao.findAllDonadores();
    }

}
