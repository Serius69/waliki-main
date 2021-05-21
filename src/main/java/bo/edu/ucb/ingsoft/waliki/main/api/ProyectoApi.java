package bo.edu.ucb.ingsoft.waliki.main.api;

import bo.edu.ucb.ingsoft.waliki.main.bl.ProyectoBl;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;

@RestController
public class ProyectoApi {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ProyectoBl proyectoBl;
    //Pagina principal
    @GetMapping(path = "/paginaprincipal")
    public ResponseDto  paginaPrincipal() {
        return new ResponseDto( true, proyectoBl.paginaPrincipal(), "Listado de todos los donadores");
    }
    //Crear nuevo proyecto
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
    @GetMapping(path = "estado/{estadoId}")
    public ResponseDto findProyecto(@PathVariable Integer estadoId) {
        if (estadoId==1 ) {  // Proyectos vigentes
            return new ResponseDto(true, proyectoBl.findProyectoVigente(estadoId), "Proyectos vigentes");
        }
        if (estadoId==2 ) {  // Proyectos en proceso
            return new ResponseDto(true, proyectoBl.findProyectoEnProceso(estadoId), "Proyectos en proceso");
        }
        if (estadoId==3 ) {  // Proyectos Finalizados
            return new ResponseDto(true, proyectoBl.findProyectosFinalizado(estadoId), "Proyectos finalizados ");
        }
        if (estadoId==4 ) {  // Todos los proyectos
            return new ResponseDto(true, proyectoBl.findAllProyectos(), "Todos los proyectos");
        }
        return new ResponseDto(true, proyectoBl.findProyectoVigente(estadoId), "Proyectos vigentes");
    }
    // BUscar un proyecto por el id
    @GetMapping(path = "/nombreProyecto/{nombre_proyecto}")
    public ResponseDto findPersonaById(@PathVariable String nombre_proyecto) {
        ProyectoDto proyecto = proyectoBl.findProyectoByName(nombre_proyecto);
        if (proyecto != null) {
            return new ResponseDto( true, proyecto, null);
        } else {
            return new ResponseDto( false, null, "No existe la persona con codigo:");
        }
    }
    // BUscar un proyecto por el id
    @GetMapping(path = "/proyecto/{proyectoId}")
    public ResponseDto findProyectoById(@PathVariable Integer proyectoId) {
        ConsultaProyectoDto proyecto = proyectoBl.findProyectobyId(proyectoId);
        if (proyecto != null) {
            return new ResponseDto( true, proyecto, null);
        } else {
            return new ResponseDto( false, null, "No existe la persona con codigo:");
        }
    }
}
