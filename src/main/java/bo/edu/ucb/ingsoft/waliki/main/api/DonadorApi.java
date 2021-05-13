package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.DonadorBl;
import bo.edu.ucb.ingsoft.waliki.main.bl.UsuarioBl;
import bo.edu.ucb.ingsoft.waliki.main.dto.DonacionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;

@RestController
public class DonadorApi {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private UsuarioBl usuarioBl;
    @Autowired
    private DonadorBl donadorBl;

    @PostMapping(path = "/donacion")
    public DonacionDto addDonacion(@RequestBody DonacionDto donacion) {
        // Validar que los datos enviados son correctos.
        if (donacion.proyectoId == null) {  // id-contrato: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El numero de telefono debe ser obligatorio");
        }
        if (donacion.donadorId == null) {  // id_usuario: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electronico debe ser obligatorio");
        }
        if (donacion.monto == null) {  // id_usuario: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electronico debe ser obligatorio");
        }
        if (donacion.hora == null ) {  // id_usuario: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electronico debe ser obligatorio");
        }
        if (donacion.fecha_donacion == null) {  // id_usuario: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electronico debe ser obligatorio");
        }

        return donadorBl.addDonacion(donacion);
    }

}
