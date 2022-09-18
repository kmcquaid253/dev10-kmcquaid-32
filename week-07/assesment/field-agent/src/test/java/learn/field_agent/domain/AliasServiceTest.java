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

}
