package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepository;
import learn.solar.data.PanelRepositoryTestDouble;
import learn.solar.models.Panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PanelServiceTest {
    PanelService toTest;


    @Test
    void shouldNotAddNullCustomer() throws DataAccessException {
        //Arrange
        //we won't set the id, because that shouldn't matter
        //since the user won't be able to in the real app

        Panel toAdd = null;

        //Act
        PanelResult result = toTest.addPanel(toAdd);

        //Assert
        assertFalse( result.isSuccess() );

        Panel payload = result.getPayLoad();

        assertNull( payload );

        String errorMessage = result.getErrorMessages().get(0);

        assertEquals("Cannot add null Panel.", errorMessage);

    }
}
