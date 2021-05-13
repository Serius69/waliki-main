package bo.edu.ucb.ingsoft.waliki.main.dao;

import bo.edu.ucb.ingsoft.waliki.main.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;

    public PersonaDto crearPersona (PersonaDto persona) {
        persona.personaId = sequenceDao.getPrimaryKeyForTable("persona");
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO persona VALUES ("
                    + persona.personaId +", '"
                    + persona.nombre +"', '"
                    + persona.apellidos+"','"
                    + persona.fechaNacimimento+"','"
                    + persona.direccionId+"') "
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return persona;
    }

    public PersonaDto findPersonaById(Integer personaId) {
        PersonaDto result = new PersonaDto();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_persona, nombre_persona, apellidos FROM persona" +
                    "  WHERE id_persona = " + personaId);  //FIXME SQL INJECTION !!!!!
            if (rs.next()) {
                result.personaId = rs.getInt("id_persona");
                result.nombre = rs.getString("nombre");
                result.apellidos = rs.getString("apellidos");
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<PersonaDto> findAllPersonas() {
        List<PersonaDto> result = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_persona, nombre_persona, apellidos FROM persona");
            while (rs.next()) {
                PersonaDto persona = new PersonaDto();
                persona.personaId = rs.getInt("id_persona");
                persona.nombre = rs.getString("nombre");
                persona.apellidos = rs.getString("apellido_paterno");
                persona.direccionId = rs.getInt("id_direccion");
                persona.fechaNacimimento = rs.getString("correo_electronico");
                result.add(persona);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
