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
        p1.setPanelMaterial(Material.AMORPHOUS_SILICON);
        p1.setYearInstalled(1964);
        p1.setTracking(true);

        Panel p2 = new Panel();

        p2.setPanelId(200);
        p2.setSection("Null East West");
        p2.setRow(249);
        p2.setColumn(19);
        p2.setPanelMaterial(Material.COPPER_INDIUM_GALLIUM_SELENIDE);
        p2.setYearInstalled(1975);
        p2.setTracking(false);

        Panel p3 = new Panel();

        p2.setPanelId(300);
        p2.setSection("East Village");
        p2.setRow(154);
        p2.setColumn(147);
        p2.setPanelMaterial(Material.MONOCRYSTALLINE_SILICON);
        p2.setYearInstalled(1899);
        p2.setTracking(true);

        panels.add(p1);
        panels.add(p2);
        panels.add(p3);




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
        //copy to protect our data from setter manipulation in the tests
        Panel copy = new Panel(editedPanel);

        boolean found = false;

        for( int i = 0; i < panels.size(); i++ ){
            if( panels.get(i).getPanelId() == editedPanel.getPanelId() ){
                panels.set( i, copy);
                found = true;
                break;
            }
        }

        return found;
    }


    @Override
    public boolean deleteBySectionRowColumn(String section, int row, int column) throws DataAccessException {
        return false;
    }

    @Override
    public List<Panel> findPanelBySection(String section) throws DataAccessException {
        List<Panel> sectionPanels = new ArrayList<>();

        for( Panel toCheck : panels ){
            if( toCheck.getSection().equals(section)){
                //sectionPanels.add( new Panel(toCheck));
                sectionPanels.add(new Panel(toCheck));
            }
        }

        return sectionPanels;
    }

    @Override
    public Panel getPanelByLocation(String section, int row, int column) {
        return null;
    }
}
