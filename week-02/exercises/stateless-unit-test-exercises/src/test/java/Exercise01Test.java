import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise01Test {

    @Test
    void add() {
        assertEquals(2, Exercise01.add(1, 1));
        assertEquals(0, Exercise01.add(112, -112));
        assertEquals(-256, Exercise01.add(-206, -50));
        assertEquals(256, Exercise01.add(150, 106));
        assertEquals(17, Exercise01.add(10, 7));
        assertEquals(-5, Exercise01.add(300, -305));
    }

    @Test
    void subtract() {
        assertEquals(0, Exercise01.subtract(10, 10));
        assertEquals(5, Exercise01.subtract(10, 5));
        assertEquals(-15, Exercise01.subtract(10, 25));
        assertEquals(100000, Exercise01.subtract(100001, 1));
        assertEquals(-200, Exercise01.subtract(50, 250));
        assertEquals(13, Exercise01.subtract(40, 27));
    }

    @Test
    void multiply(){
        assertEquals(4, Exercise01.multiply(2, 2));
        assertEquals(0, Exercise01.multiply(10, 0));
        assertEquals(-150, Exercise01.multiply(-6, 25));
        assertEquals(6000, Exercise01.multiply(2000, 3));
        assertEquals(50, Exercise01.multiply(1, 50));
        assertEquals(-560, Exercise01.multiply(80, -7));

    }

    @Test
    void divide(){
        assertEquals(31, Exercise01.divide(31, 1));
        assertEquals(0, Exercise01.divide(0, 48));
        assertEquals(1, Exercise01.divide(100, 100));
        assertEquals(-10, Exercise01.divide(-30, 3));
        assertEquals(245, Exercise01.divide(9800, 40));
        assertEquals(-9, Exercise01.divide(99, -11));
    }
}