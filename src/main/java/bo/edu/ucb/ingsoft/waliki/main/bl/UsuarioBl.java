package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.DonadorDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.UsuarioDao;
import bo.edu.ucb.ingsoft.waliki.main.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioBl {

    @Autowired
    UsuarioDao usuarioDao;

    public UsuarioDto crearUsuario(UsuarioDto usuario) {
        // Computamos el numero de seguro social, conformado por los tres primeros caracteres
        // del nombre mas los tres primeros del apelliod
        //donador.donadorId = donador.nombre.substring(0,3).toUpperCase() + donador.apellido.substring(0,3);

        return usuarioDao.crearUsuario(usuario);
    }

    public UsuarioDto findDonadorById(Integer usuarioId) {
        return usuarioDao.findUsuarioById(usuarioId);
    }


}
