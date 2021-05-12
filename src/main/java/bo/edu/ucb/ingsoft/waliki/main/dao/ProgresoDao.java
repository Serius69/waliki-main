package bo.edu.ucb.ingsoft.waliki.main.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class ProgresoDao {
    @Autowired
    private DataSource dataSource;
    private SequenceDao sequenceDao;

}
