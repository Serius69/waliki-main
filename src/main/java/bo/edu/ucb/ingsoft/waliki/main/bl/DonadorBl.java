package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.ContratoDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.DonacionDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.DonadorDao;
import bo.edu.ucb.ingsoft.waliki.main.dao.UsuarioDao;
import bo.edu.ucb.ingsoft.waliki.main.dto.ContratoDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.DonacionDto;
import bo.edu.ucb.ingsoft.waliki.main.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;

public class DonadorBl {

    @Autowired
    DonacionDao donaciondao;
    @Autowired
    ContratoDao contratodao;
    public DonacionDto crearDonacion(DonacionDto donacion) {
        // Computamos el numero de seguro social, conformado por los tres primeros caracteres
        // del nombre mas los tres primeros del apelliod
        //donacion.donacionId = donacion.nombre.substring(0,3).toUpperCase() + donador.apellido.substring(0,3);

        return donaciondao.crearDonacion(donacion);
    }

    public DonacionDto findDonacionById(Integer donacionId) {
        return donaciondao.findDonacionById(donacionId);
    }

    public ContratoDto findContrato(Integer contratoId) {
        return contratodao.findContratoById(contratoId);
    }






}
