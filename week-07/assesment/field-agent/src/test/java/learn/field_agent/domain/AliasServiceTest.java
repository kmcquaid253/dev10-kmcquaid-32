package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.data.DataException;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AliasServiceTest {

    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repo;


    @Test
    void shouldNotDeleteByNonExistingId() throws DataException {

        when( repo.deleteById(100)).thenReturn( false );

        Result deleteResult = service.deleteById(100);

        assertFalse( deleteResult.isSuccess() );
        assertEquals( "Could not find alias with Id",
                deleteResult.getMessages().get(0));

    }

    @Test
    void shouldDelete() throws DataException {
        when(repo.deleteById(anyInt())).thenReturn(true);
        Result result = service.deleteById(1);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldAdd(){
        Alias alias = new Alias();
        alias.setName("Marge");
        alias.setPersona("Random text");
        alias.setAgentId(2);

        Result result = service.add(alias);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddEmptyName() {
        Alias alias = new Alias(4," ","Name is empty", 3);
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotAddNull() {
        Alias alias = null;

        Result result = service.add(alias);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals( "alias cannot be null",
                result.getMessages().get(0));
    }

    @Test
    void shouldNotAddNullName(){
        Alias alias = new Alias();

        alias.setName(null);
        alias.setPersona("Empty name test");
        alias.setAgentId(2);

        Result result = service.add(alias);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals( "Name is required",
                result.getMessages().get(0));
    }

    @Test
    void shouldNotAddZeroAgentId(){

        Alias alias = new Alias();

        alias.setName("Testing-Testing");
        alias.setPersona("Empty agent_id test");
        alias.setAgentId(0);

        Result result = service.add(alias);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals( "Alias agent_Id is required",
                result.getMessages().get(0));
    }

    @Test
    void shouldUpdate(){
        Alias alias = new Alias(1, "Quiet One", "This is mock data", 7);

        when(repo.update(alias)).thenReturn(true);

        Result result = service.update(alias);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateEmptyName() {
        Alias alias = new Alias(1, "", "Example with empty name", 1);

        Result result = service.update(alias);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals( "Name is required",
                result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateNullAgentId() {
        Alias alias = new Alias(1, "Mock", "Example with AgentId 0", 0);

        Result result = service.update(alias);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals( "Alias agent_Id is required",
                result.getMessages().get(0));
    }
}
