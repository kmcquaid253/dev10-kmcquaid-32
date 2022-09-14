package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SolarPanelDbRepository implements SolarPanelRepository{

    JdbcTemplate template;

    public SolarPanelDbRepository( JdbcTemplate template ){
        this.template = template;
    }

    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException {

        //basic SQL injection injection attack
        //AVOID
        //DONT fall for this
        //section = "'1' OR 1 = 1; DROP table panel; --"; //<-SQL injection



        List<SolarPanel> panels = template.query(
                "select * from panel where section = ?",
                    new PanelMapper(),
                    section);

        SolarPanel p1 = actual.stream().filter( sp -> sp.getId() == 1).
        return panels;
    }

    @Override
    public SolarPanel findById(int id) throws DataAccessException {
        return null;
    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException {
        return null;
    }

    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return false;
    }
}
