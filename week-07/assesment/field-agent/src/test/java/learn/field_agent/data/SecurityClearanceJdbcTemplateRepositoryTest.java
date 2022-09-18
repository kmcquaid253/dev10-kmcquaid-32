package learn.field_agent.data;

import learn.field_agent.domain.Result;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {

    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void shouldFindById() {
        SecurityClearance secret = new SecurityClearance(1, "Secret");
        SecurityClearance topSecret = new SecurityClearance(2, "Top Secret");

        SecurityClearance actual = repository.findById(1);
        assertEquals(secret, actual);

        actual = repository.findById(2);
        assertEquals(topSecret, actual);

        actual = repository.findById(3);
        assertEquals(null, actual);
    }

    @Test
    void shouldFindAll() {
        List<SecurityClearance> securityClearances = repository.getAllSecurityClearance();
        assertNotNull(securityClearances);

        assertTrue(securityClearances.size() >= 1 && securityClearances.size() <= 4);
    }

    @Test
    void shouldFindTwoSecurityClearance(){
        List<SecurityClearance> allSecurityClearance = repository.getAllSecurityClearance();
        assertEquals(2, allSecurityClearance.size());
    }

    @Test
    void shouldFindSByInputtingSpecificData() {
        List<SecurityClearance> all = repository.getAllSecurityClearance();

        assertNotNull(all);
        // Anything 2 or bigger is good since security-clearance is being added and deleted.
        assertTrue(all.size() >= 2);

        SecurityClearance expected = new SecurityClearance();
        expected.setSecurityClearanceId(1);
        expected.setName("Secret");

        // Ensure Secret is present (1 is left alone).
        // Then confirm security-clearance_id 2 exists, though its fields may have changed.
        assertTrue(all.contains(expected)
                && all.stream().anyMatch(i -> i.getSecurityClearanceId() == 2));
    }

    @Test
    void shouldFindNullSecurityClearanceOnFindByIdTen(){

        SecurityClearance actual = repository.findById(10);

        assertNull( actual );
    }

    @Test
    void shouldAdd() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Testing");
        securityClearance.setSecurityClearanceId(3);

        SecurityClearance actual = repository.add(securityClearance);

        assertNotNull(actual);
        assertEquals(securityClearance, actual);
    }

    @Test
    void shouldNotUpdateMissing(){
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setSecurityClearanceId(100);
        securityClearance.setName("WRONG");

        assertFalse(repository.update(securityClearance));
    }

    @Test
    void shouldUpdate() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setSecurityClearanceId(1);
        securityClearance.setName("Testing Update");

        assertTrue(repository.update(securityClearance));
    }

    @Test
    void shouldDeleteExisting() throws DataException {
        assertTrue(repository.deleteById(2));
    }

    @Test
    void shouldNotDeleteNonExisting() throws DataException {
        assertFalse(repository.deleteById(6000));
    }

}