package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.DonadorDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.PersonaDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.UsuarioDao;
import bo.edu.ucb.ingsoft.waliki.main.dto.DonadorDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.PersonaDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioBl {

    @Autowired
    PersonaDao personaDao;
    DonadorDao donadorDao;
    @Autowired
    UsuarioDao usuarioDao;

    public PersonaDto crearPersona(PersonaDto persona) {
        return personaDao.crearPersona(persona);
    }

    public PersonaDto findPersonaById(Integer personaId) {
        return personaDao.findPersonaById(personaId);
    }

    public List<PersonaDto> findAllPersonas() {
        return personaDao.findAllPersonas();
    }


    public UsuarioDto crearUsuario(UsuarioDto usuario) {
        return usuarioDao.crearUsuario(usuario);
    }

    public UsuarioDto findUsuarioById(Integer usuarioId) {
        return usuarioDao.findUsuarioById(usuarioId);
    }

    public DonadorDto crearDonador(DonadorDto donador) {
        return donadorDao.crearDonador(donador);
    }

    public DonadorDto findDonadorById(Integer donadorId) {
        return donadorDao.findDonadorById(donadorId);
    }
    public DonadorDto findDonadorByName(String nombreDonador) {
        return donadorDao.findDonadorByName(nombreDonador);
    }


    public List<DonadorDto> findAllDonadores() {
        return donadorDao.findAllDonadores();
    }

}
