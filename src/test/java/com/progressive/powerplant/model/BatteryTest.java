package com.progressive.powerplant.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BatteryTest {
    @Test
    void givenEmptyName_whenNew_thenShouldThrowException() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new Battery(null, new PostCode(1111), new WattCapacity(10, Unit.AH)));
        assertEquals("Name cannot be null or empty.", e.getMessage());
        var ex = assertThrows(IllegalArgumentException.class,
                () -> new Battery("", new PostCode(1111), new WattCapacity(10, Unit.AH)));
        assertEquals("Name cannot be null or empty.", ex.getMessage());
    }

    @Test
    void givenNullPostCode_whenNew_thenShouldThrowException() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new Battery("battery-1", null, new WattCapacity(10, Unit.AH)));
        assertEquals("Post code cannot be null.", e.getMessage());
    }

    @Test
    void givenNullWattCapacity_whenNew_thenShouldThrowException() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new Battery("battery-1", new PostCode(1111), null));
        assertEquals("Watt capacity cannot be null.", e.getMessage());
    }

    @Test
    void givenListOfBatteries_whenSortByName_thenShouldReturnSortedList() {
        var b1 = new Battery("battery-b", new PostCode(1111), new WattCapacity(10, Unit.AH));
        var b2 = new Battery("battery-c", new PostCode(1111), new WattCapacity(10, Unit.AH));
        var b3 = new Battery("battery-a", new PostCode(1111), new WattCapacity(10, Unit.AH));

        var sortedList = Battery.sortByName(List.of(b1, b2, b3));
        assertEquals(b3, sortedList.get(0));
        assertEquals(b1, sortedList.get(1));
        assertEquals(b2, sortedList.get(2));
    }

}