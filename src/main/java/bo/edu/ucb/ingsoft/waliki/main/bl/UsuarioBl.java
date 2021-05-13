package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.DonadorDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.PersonaDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.UsuarioDao;
import bo.edu.ucb.ingsoft.waliki.main.dto.ConsultaDto;
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
//Crea un donador
    public DonadorDto crearDonador(DonadorDto donador) {
        return donadorDao.crearDonador(donador);
    }
//Busca un donador por su id
    public DonadorDto findDonadorById(Integer donadorId) {
        return donadorDao.findDonadorById(donadorId);
    }
//Busca un donador por su nombre
    public DonadorDto findDonadorByName(String nombreDonador) {
        return donadorDao.findDonadorByName(nombreDonador);
    }
//Listado de todos los donadores
    public List<ConsultaDto> findAllDonadores() {
        return donadorDao.findAllDonadores();
    }

}
