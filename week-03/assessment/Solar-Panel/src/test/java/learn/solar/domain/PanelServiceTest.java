package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepository;
import learn.solar.data.PanelRepositoryTestDouble;
import learn.solar.models.Material;
import learn.solar.models.Panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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


    @Test
    void shouldNotUpdatePanelWithNullSection() throws DataAccessException {
//        p2.setPanelId(300);
//        p2.setSection("East Village");
//        p2.setRow(154);
//        p2.setColumn(147);
//        p2.setPanelMaterial(Material.valueOf("MONOCRYSTALLINE_SILICON"));
//        p2.setYearInstalled(1899);
//        p2.setTracking(true);

        //simulate a completed update by the user
        Panel updated = new Panel();

        updated.setPanelId(301);
        updated.setPanelMaterial(Material.COPPER_INDIUM_GALLIUM_SELENIDE);
        updated.setTracking(false);
        updated.setYearInstalled(2020);
        updated.setSection(null);
        updated.setRow(155);
        updated.setColumn(148);


        PanelResult updateResult = service.updatePanel(updated);

        assertFalse( updateResult.isSuccess() );
        assertNull( updateResult.getPayload());
        assertEquals( "Cannot have Panel with missing section.", updateResult.getMessages().get(0));
    }

    @Test
    void shouldNotUpdatePanelWithBlankSection() throws DataAccessException {

//        p2.setPanelId(200);
//        p2.setSection("Null East West");
//        p2.setRow(249);
//        p2.setColumn(19);
//        p2.setPanelMaterial(Material.valueOf("COPPER_INDIUM_GALLIUM_SELENIDE"));
//        p2.setYearInstalled(1975);
//        p2.setTracking(false);

        Panel updated = new Panel();

        updated.setPanelId(200);
        updated.setPanelMaterial(Material.COPPER_INDIUM_GALLIUM_SELENIDE);
        updated.setTracking(false);
        updated.setYearInstalled(1975);
        updated.setSection("   \t\n");
        updated.setRow(249);
        updated.setColumn(19);

        PanelResult updateResult = service.updatePanel(updated);

        assertFalse( updateResult.isSuccess() );
        assertNull( updateResult.getPayload());
        assertEquals( "Cannot have Panel with missing section.", updateResult.getMessages().get(0));
    }

    @Test
    void shouldNotUpdatePanelWithFutureYear() throws DataAccessException {
        Panel updated = new Panel();
        updated.setPanelId(200);
        updated.setPanelMaterial(Material.COPPER_INDIUM_GALLIUM_SELENIDE);
        updated.setTracking(false);
        updated.setYearInstalled(LocalDate.now().getYear() + 1);
        updated.setSection("Null East West");
        updated.setRow(249);
        updated.setColumn(19);


        PanelResult updateResult = service.updatePanel(updated);

        assertFalse( updateResult.isSuccess() );
        assertNull( updateResult.getPayload());
        assertEquals("Cannot have Panel with future year.", updateResult.getMessages().get(0));
    }

    @Test
    void shouldNotUpdatePanelWithNullMaterial() throws DataAccessException {
        //original values
//        p1.setPanelId(100);
//        p1.setSection("lol");
//        p1.setRow(10);
//        p1.setColumn(34);
//        p1.setPanelMaterial(Material.valueOf("AMORPHOUS_SILICON"));
//        p1.setYearInstalled(1964);
//        p1.setTracking(true);

        //simulate a completed update by the user
        Panel updated = new Panel();
        updated.setPanelId(100);
        updated.setPanelMaterial(null);
        updated.setTracking(true);
        updated.setYearInstalled(2964);
        updated.setSection("lol");
        updated.setRow(10);
        updated.setColumn(34);

        PanelResult updateResult = service.updatePanel(updated);

        assertFalse( updateResult.isSuccess() );
        assertNull( updateResult.getPayload());
        assertEquals("Cannot have Panel with missing Material.", updateResult.getMessages().get(0));
    }

    @Test
    void shouldNotUpdatePanelWithNonPositiveRow() throws DataAccessException {
        //original values
//        p2.setPanelId(300);
//        p2.setSection("East Village");
//        p2.setRow(154);
//        p2.setColumn(147);
//        p2.setPanelMaterial(Material.valueOf("MONOCRYSTALLINE_SILICON"));
//        p2.setYearInstalled(1899);
//        p2.setTracking(true);

        //simulate a completed update by the user
        Panel updated = new Panel();

        updated.setPanelId(300);
        updated.setPanelMaterial(Material.MONOCRYSTALLINE_SILICON);
        updated.setTracking(true);
        updated.setYearInstalled(1899);
        updated.setSection("East Village");
        updated.setRow(0);
        updated.setColumn(147);

        PanelResult updateResult = service.updatePanel(updated);

        assertFalse( updateResult.isSuccess() );
        assertNull( updateResult.getPayload());
        assertEquals("Panel row must be between 1 and 250.", updateResult.getMessages().get(0));
    }

    @Test
    void shouldNotUpdatePanelWithRowLargerThan250() throws DataAccessException {
//        p2.setPanelId(200);
//        p2.setSection("Null East West");
//        p2.setRow(249);
//        p2.setColumn(19);
//        p2.setPanelMaterial(Material.valueOf("COPPER_INDIUM_GALLIUM_SELENIDE"));
//        p2.setYearInstalled(1975);
//        p2.setTracking(false);

        Panel updated = new Panel();
        updated.setPanelId(200);
        updated.setPanelMaterial(Material.COPPER_INDIUM_GALLIUM_SELENIDE);
        updated.setTracking(false);
        updated.setYearInstalled(1975);
        updated.setSection("Null East West");
        updated.setRow(251);
        updated.setColumn(19);

        PanelResult updateResult = service.updatePanel(updated);

        assertFalse( updateResult.isSuccess() );
        assertNull( updateResult.getPayload());
        assertEquals("Panel row must be between 1 and 250.", updateResult.getMessages().get(0));
    }

}
