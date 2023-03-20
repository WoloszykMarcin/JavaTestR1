package pl.kurs.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquareTest {
    @Test
    public void shouldCreateSquareWithValidSide() {
        //given
        double side = 5.0;
        //when
        Square square = Square.create(side);
        //then
        assertNotNull(square);
        assertEquals(side, square.getA(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCreatingSquareWithNegativeSide() {
        //given
        double side = -5.0;
        //when
        Square.create(side);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCreatingSquareWithSide0() {
        //given
        double side = 0;
        //when
        Square.create(side);
    }
}