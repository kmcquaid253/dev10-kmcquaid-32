package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
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



//        List<SolarPanel> panels = template.query(
//                "select * from panel where section = ?",
//                    new PanelMapper(),
//                    section);
//
//        SolarPanel p1 = actual.stream().filter( sp -> sp.getId() == 1).
//        return panels;
        //return template.query("select * from panel where section = ?", new PanelMapper(), section);
        return template.query( "select * from panel where section = ?", new PanelMapper(), section);
    }

    @Override
    public SolarPanel findById(int id) throws DataAccessException {
        return template.query( "select * from panel where panel_id = ?", new PanelMapper(), id )
                .stream().findFirst().orElse(null);
    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException {
        //the database will generate a key
        //we need a "holder"
        KeyHolder holder = new GeneratedKeyHolder();
        template.update((connection) -> {
                    PreparedStatement toReturn = connection.prepareStatement(
                    "insert into panel (section, `row`, col, year_installed, material, isTracking) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

                toReturn.setString(1, solarPanel.getSection());
                toReturn.setInt(2, solarPanel.getRow());
                toReturn.setInt(3, solarPanel.getColumn());
                toReturn.setInt(4, solarPanel.getYearInstalled());
                toReturn.setString(5, solarPanel.getMaterial().toString());
                toReturn.setBoolean(6, solarPanel.isTracking());

                return toReturn;
                }


            , holder);

                    solarPanel.setId(holder.getKey().intValue());


        return solarPanel;
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
