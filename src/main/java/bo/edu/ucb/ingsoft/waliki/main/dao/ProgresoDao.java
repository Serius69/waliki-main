package bo.edu.ucb.ingsoft.waliki.main.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class ProgresoDao {
    @Autowired
    private DataSource dataSource;
    private SequenceDao sequenceDao;

}
