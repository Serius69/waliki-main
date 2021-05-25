package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.*;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;

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

    //Crear un nuevo donador
    @PostMapping(path = "/donador")
    public ResponseDto createDonador(@RequestBody DonadorDto donador) {
        // Validar que los datos enviados son correctos.
        if (donador.getNombreUsuario() == null || donador.getNombreUsuario().trim().equals("") ) {  // nombre usuario: "     "
            return new ResponseDto( false, null, "El nombre de usuario es obligatori");
        }
        if (donador.getCorreoElectronico() == null || donador.getCorreoElectronico().trim().equals("") ) {  // correo electronico: "     "
            return new ResponseDto( false, null, "El correo electronico es obligatorio");
        }
        if (donador.getNumeroTelefono() == null || donador.getNumeroTelefono()<0) {  // numero telefnono: "     "
            return new ResponseDto( false, null, "El numero de telefono es obligatorio y mayor a 0");
        }
        if (donador.getDireccion() == null || donador.getDireccion().trim().equals("")) {  // direccion: "     "
            return new ResponseDto( false, null, "la direccion es obligatoria");
        }
        if (donador.getContrasena() == null || donador.getContrasena().trim().equals("")) {  // contrasenia: "     "
            return new ResponseDto( false, null, "La contrasenia es obligatoria");
        }
        return new ResponseDto( true, donadorBl.crearDonador(donador), "Donador agregado correctamente");
    }
    //Busqueda de contrado por su id
    @GetMapping(path = "/contrato/{contratoId}")
    public ContratoDto findContrato (@PathVariable Integer contratoId) {
        ContratoDto contrato = donadorBl.findContratoById(contratoId);
        if (contrato == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el contrato:" + contratoId );
        } else {
            return contrato;
        }
    }
    // Buscar un donador por su ID
    @GetMapping(path = "/donador/{donadorId}")
    public ResponseDto findDonadorById(@PathVariable Integer donadorId) {
        ConsultaDonadorDto donador = donadorBl.findDonadorById(donadorId);
        if (donador != null) {
            return new ResponseDto(true, donador, "El donador solicitado");
        } else {
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el donador con nombre:" + nombre_persona);
            return new ResponseDto( false, null, "El apellido debe ser obligatorio");
        }
    }



}
