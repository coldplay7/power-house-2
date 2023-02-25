package com.progressive.powerplant.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.progressive.powerplant.model.Unit.AH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WattCapacityTest {

    @Test
    void givenNegativeValue_whenNew_thenShouldThrowException() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new WattCapacity(-1, AH));
        assertEquals("Watt capacity value cannot be less than zero.", e.getMessage());
    }

    @Test
    void givenNullUnit_whenNew_thenShouldThrowException() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new WattCapacity(1, null));
        assertEquals("Watt capacity unit cannot be null.", e.getMessage());
    }

    @Test
    void givenListOfWattCapacity_whenTotal_shouldReturnSum() {
        var w1 = new WattCapacity(100, AH);
        var w2 = new WattCapacity(200, AH);
        assertEquals(300, WattCapacity.totalCapacity(List.of(w1, w2)));
    }

    @Test
    void givenListOfWattCapacity_whenAvg_shouldReturnAvg() {
        var w1 = new WattCapacity(100, AH);
        var w2 = new WattCapacity(200, AH);
        assertEquals(150, WattCapacity.avgCapacity(List.of(w1, w2)));
    }

}