package com.progressive.powerplant.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostCodeTest {

    @Test
    void givenNullCode_whenNew_thenShouldThrowException() {
        var e = assertThrows(IllegalArgumentException.class, () -> new PostCode(null));
        assertEquals("Post code cannot null.", e.getMessage());
    }

    @Test
    void givenTwoDigitCode_whenNew_thenShouldThrowException() {
        var e = assertThrows(IllegalArgumentException.class, () -> new PostCode(999));
        assertEquals("Post code cannot be less than 4 digit.", e.getMessage());
    }

}