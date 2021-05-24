package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.*;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonadorBl {
    @Autowired
    DonadorDao donadorDao;
    @Autowired
    ContratoDao contratodao;
    //Buscar un contrato por su id
    public ContratoDto findContratoById(Integer contratoId) {
        return contratodao.findContratoById(contratoId);
    }
    //Listado donadores
    public List<ConsultaDonadorDto> findAllDonadores() {
        return donadorDao.findAllDonadores();
    }
    //Busca un donador por su id
    public ConsultaDonadorDto findDonadorById(Integer idDonador) {
        return donadorDao.findDonadorById(idDonador);
    }
    //Crea un donador
    public DonadorDto crearDonador(DonadorDto donador) {
        return donadorDao.crearDonador(donador);
    }

}
