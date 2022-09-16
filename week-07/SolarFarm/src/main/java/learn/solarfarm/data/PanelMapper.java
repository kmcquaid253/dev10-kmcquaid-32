package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PanelMapper implements RowMapper<SolarPanel> {
    @Override
    public SolarPanel mapRow(ResultSet rowData, int rowNum) throws SQLException {
        SolarPanel toReturn = new SolarPanel();

        //we'll set every field in our object

        toReturn.setId( rowData.getInt("panel_id") );
        toReturn.setSection( rowData.getString("section"));
        toReturn.setRow( rowData.getInt("row"));
        toReturn.setColumn(rowData.getInt("col"));
        toReturn.setYearInstalled(rowData.getInt("year_installed"));
//
//        //for material we'll read a string
//        //and parse with valueOf()
        toReturn.setMaterial(Material.valueOf(rowData.getString("material")) );
//
//        //direct way
        toReturn.setTracking( rowData.getBoolean("isTracking") );

        //indirect way
        //toReturn.setTracking( rowData.getInt("isTracking") != 0 );

        //if we had y/n as values instead
        //toReturn.setTracking( rowData.getString("isTracking").equalsIgnoreCase("y") );


        return toReturn;
    }
}
