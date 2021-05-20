package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.*;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class DonadorApi {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private DonadorBl donadorBl;

    @GetMapping(path = "/donador")
    public ResponseDto  findAllDonadores() {
        return new ResponseDto( true, donadorBl.findAllDonadores(), "Listado de todos los donadores");

    }
    @PostMapping(path = "/donacion")
    public DonacionDto addDonacion(@RequestBody DonacionDto donacion) {
        // Validar que los datos enviados son correctos.
        if (donacion.proyectoId == null ) {  // id-proyecto: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo del proyecto debe ser obligatorio");
        }
        if (donacion.donadorId == null ) {  // donadorId: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ccodigo del donador debe ser obligatorio");
        }
        if (donacion.monto == null ) {  // monto: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El monto debe ser obligatorio");
        }
        if (donacion.hora == null || donacion.hora.trim().equals("") ) {  // id_usuario: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la hora debe ser obligatoria");
        }
        if (donacion.fecha_donacion == null || donacion.fecha_donacion.trim().equals("") ) {  // id_usuario: "     "
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de donacion debe debe ser obligatoria");
        }
        return donadorBl.addDonacion(donacion);
    }

}
