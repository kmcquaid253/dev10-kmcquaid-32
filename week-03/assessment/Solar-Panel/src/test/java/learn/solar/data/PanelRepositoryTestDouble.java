package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelRepositoryTestDouble implements PanelRepository{

    List<Panel> panels = new ArrayList<>();

    public PanelRepositoryTestDouble(){
        //build test data
        //this is what we'll expect at the start
        //of every service test

        Panel p1 = new Panel();

        p1.setPanelId(100);
        p1.setSection("lol");
        p1.setRow(10);
        p1.setColumn(34);
        p1.setPanelMaterial(Material.valueOf("AMORPHOUS_SILICON"));
        p1.setYearInstalled(1964);
        p1.setTracking(true);

        Panel p2 = new Panel();

        p2.setPanelId(200);
        p2.setSection("Null East West");
        p2.setRow(249);
        p2.setColumn(19);
        p2.setPanelMaterial(Material.valueOf("COPPER_INDIUM_GALLIUM_SELENIDE"));
        p2.setYearInstalled(1975);
        p2.setTracking(false);

        Panel p3 = new Panel();
//
//        p2.setPanelId(300);
//        p2.setSection("East Village");
//        p2.setRow(154);
//        p2.setColumn(147);
//        p2.setPanelMaterial(Material.valueOf("MONOCRYSTALLINE_SILICON"));
//        p2.setYearInstalled(1899);
//        p2.setTracking(true);

        panels.add(p1);
        panels.add(p2);




    }

    @Override
    public List<Panel> findAll() {
        return new ArrayList<>(panels);
    }

    @Override
    public Panel add(Panel toAdd) throws DataAccessException {
        panels.add(toAdd);
        return toAdd;
    }

    @Override
    public boolean update(Panel editedPanel) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteBySectionRowColumn(String section, int row, int column) throws DataAccessException {
        return false;
    }

    @Override
    public List<Panel> findPanelBySection(String section) throws DataAccessException {
        return null;
    }

    @Override
    public Panel getPanelByLocation(String section, int row, int column) {
        return null;
    }
}
