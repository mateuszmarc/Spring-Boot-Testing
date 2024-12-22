package com.example.demo.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    @Test
    void hello() {
        HelloController helloController = new HelloController();

        String message = helloController.hello();

        assertEquals("Hello World", message);
    }
}