package learn.field_agent.data;

import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static learn.field_agent.data.AgentJdbcTemplateRepositoryTest.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AliasJdbcTemplateRepositoryTest {

    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldAdd() {
        Alias alias = new Alias();

        alias.setName("Testing");
        alias.setPersona("Testing text");
        alias.setAgentId(5);

        Alias actual = repository.add(alias);

        assertNotNull(actual);
        assertEquals(alias, actual);
    }

    @Test
    void shouldUpdate() {
        Alias alias = new Alias();

        alias.setAliasId(2);
        alias.setName("Testing Update");
        alias.setPersona("Mock data");
        alias.setAgentId(2);

        assertTrue(repository.update(alias));
    }

    @Test
    void shouldNotUpdateMissing(){
        Alias alias = new Alias();
        alias.setAliasId(100);
        alias.setName("WRONG");
        alias.setPersona("Mock data");
        alias.setAgentId(2);

        assertFalse(repository.update(alias));
    }

    @Test
    void shouldDeleteNonExistingExisting() throws DataException {
        assertFalse(repository.deleteById(200));
    }

    @Test
    void shouldDeleteExistingById() throws DataException {
        assertTrue(repository.deleteById(1));
    }


}
