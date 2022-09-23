package learn.field_agent.data;

import learn.field_agent.domain.Result;
import learn.field_agent.models.Agency;
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
        SecurityClearance name = repository.findById(1);
        assertEquals("Secret", name.getName());
    }

    @Test
    void shouldFindAll() {
        List<SecurityClearance> securityClearances = repository.getAllSecurityClearance();
        assertNotNull(securityClearances);

        assertTrue(securityClearances.size() >= 1 && securityClearances.size() <= 4);
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