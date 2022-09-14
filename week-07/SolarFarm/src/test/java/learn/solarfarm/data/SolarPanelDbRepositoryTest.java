package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolarPanelDbRepositoryTest {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(DbTestConfig.class);

    SolarPanelDbRepository repoToTest;


    @BeforeEach
    void setup() {
        JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
        //because we're not retrieving rows of data
        //we'll use update for our sql statement
//        template.update("truncate panel");
//        int rowsAffected = template.update(
//                "insert into panel (section, `row`, col, year_installed, material, isTracking)\n" +
//                "values('Main House', 50, 50, 2000, 'POLY_SI', false),\n" +
//                "\t\t('Hill', 51, 51, 2012, 'A_SI', true),\n" +
//                "        ('Main House', 1, 1, 2020, 'CIGS', true);");
        template.update( "call set_known_good_state()");

        repoToTest = new SolarPanelDbRepository(template);
    }

    @Test
    void shouldFindTwoPanelsInMainHouseSection() throws DataAccessException {
        List<SolarPanel> actual = repoToTest.findBySection("Main House");

        //this only checks that we get the right number of objects
        // the repo could be doing anything
        assertEquals(2, actual.size()); // seed data has 2 shareable memories
    }

    @Test
    void findById() {
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

