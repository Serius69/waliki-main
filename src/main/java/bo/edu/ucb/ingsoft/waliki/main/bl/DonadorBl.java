package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.*;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonadorBl {
    @Autowired
    DonacionDao donaciondao;
    @Autowired
    DonadorDao donadorDao;
    @Autowired
    ContratoDao contratodao;
    public DonacionDto addDonacion(DonacionDto donacion) {
         return donaciondao.crearDonacion(donacion);
    }

    public DonacionDto findDonacionById(Integer donacionId) {
        return donaciondao.findDonacionById(donacionId);
    }

    public ContratoDto findContratoById(Integer contratoId) {
        return contratodao.findContratoById(contratoId);
    }
    //Listado donadores
    public List<ConsultaDto> findAllDonadores() {
        return donadorDao.findAllDonadores();
    }
    //Busca un donador por su nombre
    public DonadorDto findDonadorByName(String nombreDonador) {
        return donadorDao.findDonadorByName(nombreDonador);
    }

    //Crea un donador
    public DonadorDto crearDonador(DonadorDto donador) {
        return donadorDao.crearDonador(donador);
    }

}
