package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.ProyectoBl;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;

@RestController
public class ProyectoApi {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private ProyectoBl proyectoBl;

    //Crear nuevo proyectp
    @PostMapping(path = "/proyecto")
    public ResponseDto addProyecto(@RequestBody ProyectoDto proyecto) {
        // Validar que los datos enviados son correctos.
        if (proyecto.getNombreProyecto() == null || proyecto.getNombreProyecto().trim().equals("") ) {  // nombre_proyecto: "     "
        //    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo del proyecto debe ser obligatorio");
            return new ResponseDto( false, null, "El nombre del proyecto debe ser obligatorio");
        }
        if (proyecto.getMontoRecaudar() == null || proyecto.getMontoRecaudar()<0 ) {  // monto recaudar mayor a 0
            //    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo del proyecto debe ser obligatorio");
            return new ResponseDto( false, null, "El monto a recaudar debe ser obligatorio y mayor a 0");
        }
        if (proyecto.getDescripcion() == null || proyecto.getDescripcion().trim().equals("") ) {  // nombre_proyecto: "     "
            //    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codigo del proyecto debe ser obligatorio");
            return new ResponseDto( false, null, "La descripcion debe ser obligatoria");
        }

        return new ResponseDto(true, proyectoBl.addProyecto(proyecto), "Proyecto agregado con exito");
    }

    //Listado proyecto vigente
    @GetMapping(path = "/proyecto/estado/{estadoId}")
    public ResponseDto findProyecto(@PathVariable Integer estadoId) {
        if (estadoId==1 ) {  // nombre_proyecto: "     "
            return new ResponseDto(true, proyectoBl.findProyectoVigente(estadoId), "Proyectos vigentes");
        }
        if (estadoId==2 ) {  // nombre_proyecto: "     "
            return new ResponseDto(true, proyectoBl.findProyectoEnProceso(estadoId), "Proyectos en proceso");
        }
        if (estadoId==3 ) {  // nombre_proyecto: "     "
            return new ResponseDto(true, proyectoBl.findProyectosFinalizados(estadoId), "Proyectos finalizados ");
        }
        if (estadoId==4 ) {  // nombre_proyecto: "     "
            return new ResponseDto(true, proyectoBl.findAllProyectos(estadoId), "Todos los proyectos");
        }
        return new ResponseDto(true, proyectoBl.findProyectoVigente(estadoId), "Proyectos vigentes");
    }
    /*
    //Listado proyecto en proceso
    @GetMapping(path = "/estado/{estadoId}")
    public ResponseDto findProyectoEnProceso(@PathVariable Integer estadoId) {
        return new ResponseDto(true, proyectoBl.findProyectoEnProceso(estadoId), "Proyectos en proceso");
    }
    //Listado proyecto finalizado
    @GetMapping(path = "/estado/{estadoId}")
    public ResponseDto  findProyectoFinalizado(@PathVariable Integer estadoId) {
        return new ResponseDto(true, proyectoBl.findProyectosFinalizados(estadoId), "Proyectos finalizados");
    }
    //Listado proyecto todos
    @GetMapping(path = "/estado/{estadoId}")
    public ResponseDto  findAllProyectos(@PathVariable Integer estadoId) {
        return new ResponseDto(true, proyectoBl.findAllProyectos(estadoId), "Todos los proyectos");
    }

    //Buscar proyecto por nombre
    @GetMapping(path = "/proyecto/{nombreproyecto}")
    public ResponseDto  findProyectoByName(@PathVariable String nombreproyecto) {
        ProyectoDto proyecto = proyectoBl.findProyectoByName(nombreproyecto);
        if (proyecto != null) {
            return new ResponseDto( true, proyecto, null);
        } else {
            return new ResponseDto( false, null, "No existe la persona con codigo:");
        }
    }
    */


}
