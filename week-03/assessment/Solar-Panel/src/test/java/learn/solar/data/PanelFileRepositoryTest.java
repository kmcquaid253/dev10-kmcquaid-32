package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PanelFileRepositoryTest {
    private static final String SEED_PATH = "./data/panels-seed.txt";
    private static final String TEST_PATH = "./data/panels-test.txt";


    private PanelFileRepository repository = new PanelFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Files.copy(Paths.get(SEED_PATH), Paths.get(TEST_PATH), StandardCopyOption.REPLACE_EXISTING);
    }

    //Write test to make sure panels are fetched with the findAll()
    @Test
    void shouldFindFivePanels(){
        List<Panel> actual = repository.findAll();

        //assert it's not null
        assertNotNull(actual);
        assertEquals(5, actual.size());// Want five panels inside the result
    }

    @Test
    void shouldAddPanel() throws DataAccessException {
        //panelId|section|row|column|panelMaterial|yearInstalled|isTracking
        Panel panel = new Panel();
        panel.setSection("Main House");
        panel.setRow(156);
        panel.setColumn(87);
        panel.setPanelMaterial(Material.AMORPHOUS_SILICON);
        panel.setYearInstalled(2020);
        panel.setTracking(true);

        Panel actual = repository.add(panel);

        assertNotNull(actual);
        assertEquals(6, actual.getPanelId());
    }

    @Test
    void shouldNotUpdateMissing()throws  DataAccessException{
        Panel panel = new Panel();
        panel.setPanelId(90);

        boolean success = repository.update(panel);
        assertFalse(success);
    }

    @Test
    void shouldUpdateExisting() throws  DataAccessException{
        Panel panel = new Panel();
        panel.setPanelId(3);
        panel.setSection("Testing House");
        panel.setPanelMaterial(Material.AMORPHOUS_SILICON);
        panel.setColumn(222);
        panel.setRow(111);
        panel.setYearInstalled(2020);
        panel.setTracking(true);

        boolean actual = repository.update(panel);
        assertTrue(actual);
    }

    @Test
    void shouldFindTwo() throws DataAccessException{
        List<Panel> actual = repository.findPanelBySection("Main House");
        assertEquals(3, actual.size()); // seed data has 2 shareable memories
    }

    @Test
    void shouldFindOne() throws DataAccessException{
        List<Panel> actual = repository.findPanelBySection("East House");
        assertEquals(2, actual.size()); // seed data has 2 shareable memories
    }

    @Test
    void shouldDeleteExisting() throws DataAccessException{
        boolean actual = repository.deleteBySectionRowColumn("East House", 143, 1);
        assertTrue(actual);

    }

    @Test
    void shouldNotDeleteMissing() throws DataAccessException{
        boolean actual = repository.deleteBySectionRowColumn("Te", 1, 2);
        assertFalse(actual);
    }


}
