package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.PersonaDao;
import bo.edu.ucb.ingsoft.waliki.main.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaBl {

    @Autowired
    PersonaDao personaDao;

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

}

