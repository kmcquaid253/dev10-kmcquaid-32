package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepository;
import learn.solar.data.PanelRepositoryTestDouble;
import learn.solar.models.Material;
import learn.solar.models.Panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PanelServiceTest {
    PanelService service;

    @BeforeEach
    void setup() {
        PanelRepositoryTestDouble repository = new PanelRepositoryTestDouble();
        service = new PanelService(repository);
    }


    @Test
    void shouldNotAddNullPanel() throws DataAccessException {
        PanelResult result = service.addPanel(null);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldAdd() throws DataAccessException {
        Panel panel = new Panel();
        panel.setSection("Zonda Itscowics");
        panel.setRow(4);
        panel.setColumn(6);
        panel.setPanelMaterial(Material.valueOf("AMORPHOUS_SILICON"));
        panel.setYearInstalled(1990);
        panel.setTracking(true);

        PanelResult result = service.addPanel(panel);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddPanelWithLargeRow() throws DataAccessException {
        Panel panel = new Panel();
        panel.setSection("Zonda Western");
        panel.setRow(251);
        panel.setColumn(10);
        panel.setPanelMaterial(Material.valueOf("CADMIUM_TELLURIDE"));
        panel.setYearInstalled(1990);
        panel.setTracking(true);

        PanelResult result = service.addPanel(panel);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddPanelWithNonPositiveRow() throws DataAccessException{
        Panel panel = new Panel();
        panel.setSection("Manor Farm");
        panel.setRow(-90);
        panel.setColumn(17);
        panel.setPanelMaterial(Material.valueOf("MULTICRYSTALLINE_SILICON"));
        panel.setYearInstalled(1980);
        panel.setTracking(false);

        PanelResult result = service.addPanel(panel);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddPanelWithLargeCol() throws DataAccessException{
        Panel panel = new Panel();
        panel.setSection("West-East Valley");
        panel.setRow(68);
        panel.setColumn(300);
        panel.setPanelMaterial(Material.valueOf("AMORPHOUS_SILICON"));
        panel.setYearInstalled(2011);
        panel.setTracking(false);

        PanelResult result = service.addPanel(panel);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddPanelWithNonPositiveCol() throws DataAccessException{
        Panel panel = new Panel();
        panel.setSection("Ranch Valley");
        panel.setRow(10);
        panel.setColumn(-7);
        panel.setPanelMaterial(Material.valueOf("COPPER_INDIUM_GALLIUM_SELENIDE"));
        panel.setYearInstalled(1990);
        panel.setTracking(true);

        PanelResult result = service.addPanel(panel);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddPanelWithFutureInstallYear() throws DataAccessException{
        Panel panel = new Panel();

        panel.setSection("Great Farms");
        panel.setRow(111);
        panel.setColumn(60);
        panel.setPanelMaterial(Material.valueOf("COPPER_INDIUM_GALLIUM_SELENIDE"));
        panel.setYearInstalled(2023);
        panel.setTracking(false);

        PanelResult result = service.addPanel(panel);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddPanelWithNullMaterial() throws DataAccessException {
        Panel panel = new Panel();

        panel.setSection("Ivy Ridge");
        panel.setRow(203);
        panel.setColumn(242);
        panel.setPanelMaterial(null);
        panel.setYearInstalled(1908);
        panel.setTracking(false);

        PanelResult result = service.addPanel(panel);

        assertFalse(result.isSuccess());
    }


}
