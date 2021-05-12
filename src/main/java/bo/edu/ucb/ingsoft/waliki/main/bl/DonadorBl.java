package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.DonadorDao;
import bo.edu.ucb.ingsoft.waliki.main.dto.DonadorDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DonadorBl {

    @Autowired
    DonadorDao donadorDao;

    public DonadorDto crearDonador(DonadorDto donador) {
        // Computamos el numero de seguro social, conformado por los tres primeros caracteres
        // del nombre mas los tres primeros del apelliod
        //donador.donadorId = donador.nombre.substring(0,3).toUpperCase() + donador.apellido.substring(0,3);

        return donadorDao.crearDonador(donador);
    }

    public DonadorDto findDonadorById(Integer donadorId) {
        return donadorDao.findDonadorById(donadorId);
    }

    public List<DonadorDto> findAllDonadores() {
        return donadorDao.findAllDonadores();
    }

}
