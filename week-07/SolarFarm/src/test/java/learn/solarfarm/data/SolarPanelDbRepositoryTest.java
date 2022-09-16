package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SolarPanelDbRepositoryTest {

    @Autowired
    SolarPanelDbRepository repoToTest;

    @Autowired
    JdbcTemplate template;

    static boolean hasRun = false;

    @BeforeEach
    void setup() {
        if (!hasRun) {
            hasRun = true;
            template.update("call set_known_good_state();");
        }
    }

    @Test
    void shouldFindTwoPanelsInMainHouseSection() throws DataAccessException {
        List<SolarPanel> actual = repoToTest.findBySection("Main House");

        //this ONLY checks that we get the right number of objects
        //the repo could be doing anything...
        assertEquals(2, actual.size());

        SolarPanel p1 = actual.stream().filter( sp -> sp.getId() == 1 ).findFirst().orElse(null);
        SolarPanel p2 = actual.stream().filter( sp -> sp.getId() == 3 ).findFirst().orElse(null);

        assertEquals( 1, p1.getId());
        assertEquals("Main House", p1.getSection());
        assertEquals(50, p1.getRow());
        assertEquals(50, p1.getColumn());
        assertEquals(2000, p1.getYearInstalled());
        assertEquals(Material.POLY_SI, p1.getMaterial());
        assertFalse( p1.isTracking() );

        assertEquals(3, p2.getId());
        assertEquals("Main House", p2.getSection());
        assertEquals(1, p2.getRow());
        assertEquals(1, p2.getColumn());
        assertEquals(2020, p2.getYearInstalled());
        assertEquals(Material.CIGS, p2.getMaterial());
        assertTrue( p2.isTracking());

    }

    @Test
    void shouldFindNullPanelOnFindByIdFive() throws DataAccessException {

        SolarPanel actual = repoToTest.findById(5);

        assertNull( actual );
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}