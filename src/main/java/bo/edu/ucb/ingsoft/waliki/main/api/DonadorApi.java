package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.*;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(path = "/donacion")
    public ResponseDto addDonacion(@RequestBody DonacionDto donacion) {
        // Validar que los datos enviados son correctos.
        if (donacion.proyectoId == null ) {  // id-proyecto: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo del proyecto debe ser obligatorio");
            return new ResponseDto( false, null, "El codigo del proyecto debe ser obligatorio");
        }
        if (donacion.donadorId == null ) {  // donadorId: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ccodigo del donador debe ser obligatorio");
            return new ResponseDto( false, null, "El ccodigo del donador debe ser obligatorio");
        }
        if (donacion.monto == null ) {  // monto: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El monto debe ser obligatorio");
            return new ResponseDto( false, null, "El nombre debe ser obligatorio");
        }
        if (donacion.hora == null || donacion.hora.trim().equals("") ) {  // id_usuario: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la hora debe ser obligatoria");
            return new ResponseDto( false, null, "la hora debe ser obligatoria");
        }
        if (donacion.fecha_donacion == null || donacion.fecha_donacion.trim().equals("") ) {  // id_usuario: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de donacion debe debe ser obligatoria");
            return new ResponseDto( false, null, "La fecha de donacion debe debe ser obligatoria");
        }
        return new ResponseDto( true, donadorBl.addDonacion(donacion), "Donacion agregado correctamente");
    }
    @PostMapping(path = "/donador")
    public ResponseDto createDonador(@RequestBody DonadorDto donador) {
        // Validar que los datos enviados son correctos.
        if (donador.getNombreUsuario() == null || donador.getNombreUsuario().trim().equals("") ) {  // nombre usuario: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario es obligatorio");
            return new ResponseDto( false, null, "El nombre de usuario es obligatori");
        }
        if (donador.getCorreoElectronico() == null || donador.getCorreoElectronico().trim().equals("")) {  // correo electronico: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electronico es obligatorio");
            return new ResponseDto( false, null, "El correo electronico es obligatorio");
        }
        if (donador.getNumeroTelefono() == null || donador.getNumeroTelefono()<0) {  // numero telefnono: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El numero de telefono es obligatorio y mayor a 0");
            return new ResponseDto( false, null, "El numero de telefono es obligatorio y mayor a 0");
        }
        if (donador.getDireccion() == null || donador.getDireccion().trim().equals("")) {  // direccion: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la direccion es obligatoria");
            return new ResponseDto( false, null, "la direccion es obligatoria");
        }
        if (donador.getContrasena() == null || donador.getContrasena().trim().equals("")) {  // contrasenia: "     "
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contrasenia es obligatoria");
            return new ResponseDto( false, null, "La contrasenia es obligatoria");
        }
        return new ResponseDto( true, donadorBl.crearDonador(donador), "Donador agregado correctamente");
    }

    // Buscar un donador por su nombre
    @GetMapping(path = "/donador/{nombre_persona}")
    public ResponseDto findDonadorByName(@PathVariable String nombre_persona) {
        DonadorDto donador = donadorBl.findDonadorByName(nombre_persona);
        if (donador != null) {
            return new ResponseDto(true, donadorBl.findDonadorByName(nombre_persona), null);
        } else {
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el donador con nombre:" + nombre_persona);
            return new ResponseDto( false, null, "El apellido debe ser obligatorio");
        }
    }



}
