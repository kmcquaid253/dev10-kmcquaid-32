import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PangramTest {
    @Test
    void checkPangramTrue(){
        Pangram toTest = new Pangram();
        assertTrue(toTest.isPangram("the quick brown fox jumps over the lazy dog"));

    }
}