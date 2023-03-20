package pl.kurs.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CircleTest {
    @Test
    public void shouldCreateCircleWithValidRadius() {
        //given
        double radius = 5.0;
        //when
        Circle circle = Circle.create(radius);
        //then
        assertNotNull(circle);
        assertEquals(radius, circle.getR(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCreatingCircleWithNegativeRadius() {
        //given
        double radius = -5.0;
        //when
        Circle.create(radius);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCreatingCircleWithRadius0() {
        //given
        double radius = 0;
        //when
        Circle.create(radius);
    }

}