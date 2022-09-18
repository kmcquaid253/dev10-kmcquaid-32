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
import static org.mockito.Mockito.when;

@SpringBootTest
public class AliasServiceTest {

    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repo;

//    @Test
//    void shouldDeleteByExistingId() throws DataException {
//        Alias toDelete = new Alias(1, "Mist", "Quiet like mist", 4);
//        when( repo.findById(1))
//                .thenReturn( toDelete )
//                .thenReturn( null );
//        when( repo.deleteById(1))
//                .thenReturn(true);
//
//        SecurityClearance found = service.findById(1);
//        Result deleteResult = service.deleteById(1);
//        SecurityClearance shouldBeNull = service.findById(1);
//
//        assertNotNull( found );
//        assertTrue( deleteResult.isSuccess() );
//        assertNull( shouldBeNull );
//    }

    @Test
    void shouldNotDeleteByNonExistingId() throws DataException {

        when( repo.deleteById(100)).thenReturn( false );

        Result deleteResult = service.deleteById(100);

        assertFalse( deleteResult.isSuccess() );
        assertEquals( "Could not find alias with Id",
                deleteResult.getMessages().get(0));

    }
}
