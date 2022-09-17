package learn.field_agent.domain;

import learn.field_agent.data.DataException;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repo;

    @Test
    void getAllSecurityClearance() {
        List<SecurityClearance> expectedSecurityClearences = new ArrayList();
        expectedSecurityClearences.add( new SecurityClearance(1, "Secret"));
        expectedSecurityClearences.add( new SecurityClearance(2, "Top-Secret"));
        expectedSecurityClearences.add( new SecurityClearance(3, "Incognito"));


        when( repo.getAllSecurityClearance() ).thenReturn(expectedSecurityClearences);

        List<SecurityClearance> actualSecurityClearances = service.getAllSecurityClearance();

        assertArrayEquals( expectedSecurityClearences.toArray(), actualSecurityClearances.toArray() );
    }

    @Test
    void shouldFindSecretCreatedBelowById() {
        // pass-through test, probably not useful
        SecurityClearance expected = makeSecurityClearance();
        when(repo.findById(7)).thenReturn(expected);
        SecurityClearance actual = service.findById(7);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindSecurityClearanceWithAnIdOfOne(){
        SecurityClearance expected = new SecurityClearance(1, "Secret");

        when(repo.findById(1)).thenReturn(expected);

        SecurityClearance securityClearance = service.findById(1);
        assertEquals(expected, securityClearance);
    }

    @Test
    void shouldAdd(){
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Super-mega-secret");

        Result result = service.add(securityClearance);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddEmptyName() {
        SecurityClearance securityClearance = new SecurityClearance(5,"");
        Result<SecurityClearance> result = service.add(securityClearance);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotCreateNonUniqueName() {
        ArrayList<SecurityClearance> securityClearances = new ArrayList<>();
        securityClearances.add(new SecurityClearance(1, "Secret"));
        when(repo.findByName("Secret")).thenReturn(securityClearances);

        SecurityClearance clearance = new SecurityClearance();
        clearance.setName("Secret");

        Result result = service.add(clearance);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals( "Security Clearance `name` must be unique.",
                result.getMessages().get(0));
    }

    @Test
    void shouldUpdate(){
        SecurityClearance securityClearance = new SecurityClearance(1, "cool-top-secret");

        when(repo.update(securityClearance)).thenReturn(true);

        Result result = service.update(securityClearance);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateNonExistentSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setSecurityClearanceId(200);
        securityClearance.setName("Testing-secret");


        Result result = service.update(securityClearance);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotUpdateEmptyName() {
        SecurityClearance securityClearance = new SecurityClearance(1,"" );

        Result result = service.update(securityClearance);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals( "Name is required",
                result.getMessages().get(0));
    }

    @Test
    void shouldDeleteByExistingId() throws DataException {
        SecurityClearance toDelete = new SecurityClearance(1, "Secret");
        when( repo.findById(1))
                .thenReturn( toDelete )
                .thenReturn( null );
        when( repo.deleteById(1))
                .thenReturn(true);

        SecurityClearance found = service.findById(1);
        Result deleteResult = service.deleteById(1);
        SecurityClearance shouldBeNull = service.findById(1);

        assertNotNull( found );
        assertTrue( deleteResult.isSuccess() );
        assertNull( shouldBeNull );
    }

    @Test
    void shouldNotDeleteByNonExistingId() throws DataException {

        when( repo.deleteById(100)).thenReturn( false );

        Result deleteResult = service.deleteById(100);

        assertFalse( deleteResult.isSuccess() );
        assertEquals( "Could not find Security Clearance with Id ",
                deleteResult.getMessages().get(0));

    }

    SecurityClearance makeSecurityClearance() {
        //(1,'Secret'),
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setSecurityClearanceId(7);
        securityClearance.setName("Secret");
        return securityClearance;
    }
}