package bo.edu.ucb.ingsoft.waliki.main.bl;

import bo.edu.ucb.ingsoft.waliki.main.dao.*;
import bo.edu.ucb.ingsoft.waliki.main.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonadorBl {
    @Autowired
    DonacionDao donaciondao;
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

}
