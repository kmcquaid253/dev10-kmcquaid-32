package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForagerServiceTest {

    ForagerService service;

    @BeforeEach
    void setup() {
        ForagerRepositoryDouble repository = new ForagerRepositoryDouble();
        service = new ForagerService(repository);
    }

    @Test
    void shouldAddValidForager() throws DataException {

        //Arrange
        //won't set the id, because that shouldn't matter
        //since the user won't be able to in the real app

        Forager toAdd = new Forager();

        toAdd.setFirstName("Mark");
        toAdd.setLastName("Cuban");
        toAdd.setState("WI");

        //Act
        Result result = service.add(toAdd);

        //Assert
        assertTrue( result.isSuccess() );

        Forager payload = (Forager) result.getPayload();

        //without those methods we have to check field by field
        assertEquals( "Mark", payload.getFirstName());
        assertEquals( "Cuban", payload.getLastName());
        assertEquals( "WI", payload.getState());

    }

    @Test
    void shouldAdd() throws DataException{
        Forager forager = new Forager();

        forager.setId("498604db-0743-4599-a503-3d8190fda823");
        forager.setFirstName("Karen");
        forager.setLastName("Santiago");
        forager.setState("CA");

        Result result = service.add(forager);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddNullForager() throws DataException {
        Result result = service.add(null);
        assertFalse(result.isSuccess());

    }

    @Test
    void ShouldNotAddForagerWithNullFirstName() throws DataException{
        Forager forager = new Forager();

        forager.setId("498604db-b6d6-4599-a503-3d8190fda823");
        forager.setFirstName(null);
        forager.setLastName("Cuban");
        forager.setState("WI");

        Result result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void ShouldNotAddForagerWithNullLastName() throws DataException{
        Forager forager = new Forager();

        forager.setId("498604db-b6d6-4599-a503-3d8190fda823");
        forager.setFirstName("Mark");
        forager.setLastName(null);
        forager.setState("WI");

        Result result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void ShouldNotAddForagerWithNullState() throws DataException{
        Forager forager = new Forager();

        forager.setId("498604db-b6d6-4599-a503-3d8190fda823");
        forager.setFirstName("Mark");
        forager.setLastName("Cuban");
        forager.setState(null);

        Result result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void ShouldNotAddDuplicateForager() throws DataException{
        Forager forager = new Forager();

        forager.setId("03847362-b6d6-4599-a503-3d8190fda823");
        forager.setFirstName("Mark");
        forager.setLastName("Cuban");
        forager.setState("WI");

        Result result = service.add(forager);
        assertFalse(result.isSuccess());
    }
}
