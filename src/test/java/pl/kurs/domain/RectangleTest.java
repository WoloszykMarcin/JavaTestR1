package pl.kurs.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {
    @Test
    public void shouldCreateRectangleWithValidParameters() {
        //given
        double width = 4.0;
        double height = 5.0;
        //when
        Rectangle rectangle = Rectangle.create(width, height);
        //then
        assertNotNull(rectangle);
        assertEquals(width, rectangle.getLength(), 0.0);
        assertEquals(height, rectangle.getWidth(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCreatingRectangleWithNegativeWidth() {
        //given
        double width = -4.0;
        double height = 5.0;
        //when
        Rectangle.create(width, height);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCreatingRectangleWithNegativeHeight() {
        //given
        double width = 4.0;
        double height = -5.0;
        //when
        Rectangle.create(width, height);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCreatingRectangleWith0s() {
        //given
        double width = 0;
        double height = 0;
        //when
        Rectangle.create(width, height);
    }
}